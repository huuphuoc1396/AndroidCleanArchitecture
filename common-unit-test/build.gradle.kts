plugins {
    id(GradlePlugins.javaLib)
    id(GradlePlugins.kotlin)
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Modules.commonLib))

    implementation(Libs.kotlinStdlib)
    implementation(Libs.kotlinReflect)

    implementation(Libs.timber)

    implementation(TestLibs.junit)
    implementation(TestLibs.androidTestJunit)
    implementation(TestLibs.hamcrest)
    implementation(TestLibs.mockk)
    implementation(TestLibs.koin)
}