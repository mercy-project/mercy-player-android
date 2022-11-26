object Versions {
    object Android {
        const val compileSdk = 33
        const val minSdk = 23
        const val targetSdk = 31
        const val versionCode = 1
        const val versionName = "1.0"
    }
}

object Depends {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.3.1"

    object AndroidX {
        const val core = "androidx.core:core-ktx:1.6.0"
        const val appcompat = "androidx.appcompat:appcompat:1.3.1"

        object Activity {
            private const val version = "1.5.1"
            const val activity = "androidx.activity:activity-ktx:$version"
            const val compose = "androidx.activity:activity-compose:$version"
        }

        const val fragment = "androidx.fragment:fragment-ktx:1.3.6"
        const val material = "com.google.android.material:material:1.4.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.2.1"
        const val browser = "androidx.browser:browser:1.3.0"

	    private const val startupVersion = "1.1.1"
        const val startup = "androidx.startup:startup-runtime:$startupVersion"

        object Lifecycle {
            private const val lifecycleVersion = "2.4.0"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        }

        object Navigation {
            private const val navigationVersion = "2.5.1"

            const val fragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
            const val ui = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
            const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion"
        }

	    object Compose {
		    const val composeBom = "androidx.compose:compose-bom:2022.10.00"

		    // Choose one of the following:
		    // Material Design 3
		    const val material3 = "androidx.compose.material3:material3"
		    // or Material Design 2
		    const val material = "androidx.compose.material:material"
		    // or skip Material Design and build directly on top of foundational components
		    const val foundation = "androidx.compose.foundation:foundation"
		    // or only import the main APIs for the underlying toolkit systems,
		    // such as input and measurement/layout
		    const val composeUi = "androidx.compose.ui:ui"

		    // Android Studio Preview support
		    const val toolingPreview = "androidx.compose.ui:ui-tooling-preview"
		    const val uiTooling = "androidx.compose.ui:ui-tooling"
		    const val uiToolingDebug = "androidx.compose.ui:ui-tooling"

		    // UI Tests
		    const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4"
		    const val uiTestManifestDebug = "androidx.compose.ui:ui-test-manifest"

		    // Optional - Included automatically by material, only add when you need
		    // the icons but not the material library (e.g. when using Material3 or a
		    // custom design system based on Foundation)
		    const val materialIconsCore = "androidx.compose.material:material-icons-core"
		    // Optional - Add full set of material icons
		    const val materialIconsExtended = "androidx.compose.material:material-icons-extended"
		    // Optional - Add window size utils
		    const val material3WindowSize = "androidx.compose.material3:material3-window-size-class"

		    // Optional - Integration with activities
		    const val activityCompose = "androidx.activity:activity-compose:1.5.1"
		    // Optional - Integration with ViewModels
		    const val viewmodelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"

		    const val runtime = "androidx.compose.runtime:runtime"
		    // Optional - Integration with LiveData
		    const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata"
		}
    }

    object Kotlin {
        private const val kotlinVersion = "1.7.20"
        const val coroutineVersion = "1.6.4"

        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion"
        const val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:0.4.0"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1"
        const val serializationPlugin = "org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion"

        object Test {
            const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineVersion"
            const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion"
            const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion"
        }
    }

    object Square {
        const val okHttpVersion = "4.10.0"
        const val okhttp3_logging = "com.squareup.okhttp3:logging-interceptor:${okHttpVersion}"
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val serialization =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"

        object Test {
            const val okhttpMockWebServer = "com.squareup.okhttp3:mockwebserver:${okHttpVersion}"
        }
    }

    object Dagger {
        private const val hiltVersion = "2.43.2"
        const val hiltAndroid = "com.google.dagger:hilt-android:$hiltVersion"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:$hiltVersion"
        const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
    }

    object Glide {
        private const val glideVersion = "4.13.2"
        const val glide = "com.github.bumptech.glide:glide:$glideVersion"
        const val compiler = "com.github.bumptech.glide:compiler:$glideVersion"
    }

	object Firebase {
		private const val googleServicesVersion = "4.3.13"
		const val googleServicesPlugin = "com.google.gms:google-services:$googleServicesVersion"

		private const val crashlyticsGradleVersion = "2.9.1"
		const val crashlyticsGradlePlugin = "com.google.firebase:firebase-crashlytics-gradle:$crashlyticsGradleVersion"

		private const val firebaseBomVersion = "30.3.1"

		// Import the BoM for the Firebase platform
		const val firebaseBom = "com.google.firebase:firebase-bom:$firebaseBomVersion"

		// Declare the dependencies for the Crashlytics and Analytics libraries
		// When using the BoM, you don't specify versions in Firebase library dependencies
		const val firebaseCrashlyticsKtx = "com.google.firebase:firebase-crashlytics-ktx"
		const val firebaseAnalyticsKtx = "com.google.firebase:firebase-analytics-ktx"
		const val firebaseAuthKtx = "com.google.firebase:firebase-auth-ktx"

		// Google Sign In SDK
		private const val playServicesAuthVersion = "20.3.0"
		const val playServicesAuth = "com.google.android.gms:play-services-auth:$playServicesAuthVersion"
	}

    const val material = "com.google.android.material:material:1.3.0"
	const val threetenabp = "com.jakewharton.threetenabp:threetenabp:1.3.1"
    const val timber = "com.jakewharton.timber:timber:5.0.1"
    const val inject = "javax.inject:javax.inject:1"

	private const val dotsIndicatorVersion = "4.3"
	const val dotsIndicator = "com.tbuonomo:dotsindicator:$dotsIndicatorVersion"


    object Lint {
        const val ktlint = "com.pinterest:ktlint:0.47.0"
        const val detektFormatting = "io.gitlab.arturbosch.detekt:detekt-formatting:1.21.0"
    }

    object Test {
        const val junit = "junit:junit:4.13.2"
        const val androidJunit = "androidx.test.ext:junit:1.1.2"
        const val androidxCore = "androidx.test:core-ktx:1.4.0"
        const val androidxJunit = "androidx.test.ext:junit-ktx:1.1.3"
        const val androidxRules = "androidx.test:rules:1.2.0"
        const val androidxRunner = "androidx.test:runner:1.4.0"
        const val robolectric = "org.robolectric:robolectric:4.6.1"
        const val threeTenBp = "org.threeten:threetenbp:1.5.2"
        const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:4.0.0"
    }
}