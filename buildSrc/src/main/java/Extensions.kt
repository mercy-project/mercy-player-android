import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.kotlin

fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? {
    return add("testImplementation", dependencyNotation)
}

fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? {
    return add("androidTestImplementation", dependencyNotation)
}

fun DependencyHandler.addTestDependencies(kotlinVersion: String) {
    testImplementation(kotlin("test-junit", kotlinVersion))

    testImplementation(Depends.Test.junit)
    testImplementation(Depends.Test.androidxCore)
    testImplementation(Depends.Test.androidxJunit)
    testImplementation(Depends.Test.androidxRules)
    testImplementation(Depends.Test.androidxRunner)
    testImplementation(Depends.Kotlin.Test.coroutineTest)
    testImplementation(Depends.Kotlin.Test.coroutineCore)

    testImplementation(Depends.Test.threeTenBp)
    testImplementation(Depends.Square.Test.okhttpMockWebServer)
    testImplementation(Depends.Test.robolectric)
    testImplementation(Depends.Test.mockitoKotlin)
}

fun DependencyHandler.addAndroidTestDependencies(kotlinVersion: String) {
    androidTestImplementation(kotlin("test-junit", kotlinVersion))

    androidTestImplementation(Depends.Test.androidxCore)
    androidTestImplementation(Depends.Test.androidxJunit)
    androidTestImplementation(Depends.Test.androidxRules)
    androidTestImplementation(Depends.Test.androidxRunner)
    testImplementation(Depends.Kotlin.Test.coroutineAndroid)

    androidTestImplementation(Depends.Square.Test.okhttpMockWebServer)
}