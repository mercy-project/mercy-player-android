// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Depends.androidGradlePlugin)
        classpath(Depends.Kotlin.gradlePlugin)
        classpath(Depends.Kotlin.serializationPlugin)
        classpath(Depends.Dagger.hiltGradlePlugin)
        classpath(Depends.AndroidX.Navigation.safeArgs)
	    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
	    classpath(Depends.Firebase.googleServicesPlugin)
	    classpath(Depends.Firebase.crashlyticsGradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}