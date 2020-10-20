plugins {
    id(GradlePlugins.javaLib)
    id(GradlePlugins.kotlin)
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Libs.kotlinStdlib)

    implementation(Libs.timber)

    testImplementation(TestLibs.junit)
    testImplementation(TestLibs.androidTestJunit)
    testImplementation(TestLibs.archTestCore)
    testImplementation(TestLibs.hamcrest)
    testImplementation(TestLibs.mockk)
    testImplementation(TestLibs.koin)
}