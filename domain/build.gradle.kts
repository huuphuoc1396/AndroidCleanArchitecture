plugins {
    id(GradlePlugins.javaLib)
    id(GradlePlugins.kotlin)
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Modules.commonLib))

    implementation(Libs.kotlinStdlib)

    implementation(Libs.coroutinesCore)
    implementation(Libs.koinCore)
    implementation(Libs.timber)

    testImplementation(TestLibs.junit)
    testImplementation(TestLibs.hamcrest)
    testImplementation(TestLibs.mockk)
    testImplementation(TestLibs.koin)
}