import groovy.lang.ExpandoMetaClassCreationHandle.disable

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
	id("io.gitlab.arturbosch.detekt") version "1.16.0"
	id("com.google.gms.google-services")
	id("com.google.firebase.crashlytics")
}

/**
 * Major: 큰변화가 있을때 증가
 * Minor: 보통의 스프린트 단위로 증가
 * Patch: 핫픽스 처리시 증가
 */
val versionMajor = 1
val versionMinor = 0
val versionPatch = 0

android {
    compileSdk = Versions.Android.compileSdk

    defaultConfig {
        applicationId = "sideproject.mercy"
        minSdk = Versions.Android.minSdk
        targetSdk = Versions.Android.targetSdk
        versionCode = versionMajor * 10000 + versionMinor * 100 + versionPatch
        versionName = "$versionMajor.$versionMinor.$versionPatch"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "KAKAO_REST_API_AUTHORIZATION", "\"KakaoAK sometoken\"")
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    // lint {
    //     disable(
    //         "UnsafeExperimentalUsageError",
    //         "UnsafeExperimentalUsageWarning"
    //     )
    // }
}

val ktlint by configurations.creating

tasks.register<JavaExec>("verification") {
    group = "verification"
    description = "Check Kotlin code style."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args("--android", "src/**/*.kt")
}

tasks.named("check") {
    dependsOn(ktlint)
}

/** 스타일 수정 후 자동으로 수정해 줌 */
tasks.register<JavaExec>("ktlintFormat"){
    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args("-F", "src/**/*.kt")
}

/** GitHooks 복사 */
tasks.register<Copy>("copyGitHooks"){
    from("${rootDir}/codeConfig/git/git-hooks/") {
        include("**/*")
        rename("(.*)", "$1")
    }

    into("${rootDir}/.git/hooks")
}

/** GitHooks 설치 */
tasks.register<Exec>("installGitHooks"){
    group = "git hooks"
    workingDir(rootDir)
    commandLine("chmod")
    args("-R", "+x", ".git/hooks/")
    dependsOn("copyGitHooks")
}

detekt {
    toolVersion = "1.16.0"
    config = files("$rootDir/config/detekt/detekt.yml")
    buildUponDefaultConfig = true
}

kapt {
    useBuildCache = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Depends.Kotlin.serialization)
    implementation(Depends.AndroidX.appcompat)
    implementation(Depends.AndroidX.core)
    implementation(Depends.AndroidX.constraintLayout)
    implementation(Depends.AndroidX.Activity.activity)
    implementation(Depends.AndroidX.Navigation.fragment)
    implementation(Depends.AndroidX.Navigation.ui)
    implementation(Depends.AndroidX.startup)
    implementation(Depends.material)
    implementation(Depends.threetenabp)
    implementation(Depends.timber)
    implementation(Depends.inject)

    api(Depends.Square.retrofit)
    implementation(Depends.Square.serialization)
    implementation(Depends.Square.okhttp3_logging)

    implementation(Depends.Dagger.hiltAndroid)
	implementation("androidx.preference:preference:1.2.0")
	kapt(Depends.Dagger.hiltCompiler)

    implementation(Depends.Glide.glide)
    kapt(Depends.Glide.compiler)

	// Firebase
	implementation(platform(Depends.Firebase.firebaseBom))
	implementation(Depends.Firebase.firebaseCrashlyticsKtx)
	implementation(Depends.Firebase.firebaseAnalyticsKtx)

    ktlint(Depends.Lint.ktlint)
    detektPlugins(Depends.Lint.detektFormatting)

    addTestDependencies(org.jetbrains.kotlin.config.KotlinCompilerVersion.VERSION)
    addAndroidTestDependencies(org.jetbrains.kotlin.config.KotlinCompilerVersion.VERSION)
}
