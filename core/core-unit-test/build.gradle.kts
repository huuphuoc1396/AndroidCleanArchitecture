plugins {
    id(GradlePlugins.javaLib)
    id(GradlePlugins.kotlin)
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Modules.coreLib))

    implementation(KotlinLibs.kotlinStdlib)
    implementation(KotlinLibs.kotlinReflect)

    implementation(AndroidTestLibs.junit)
    implementation(AndroidTestLibs.androidTestJunit)
    implementation(AndroidTestLibs.archTestCore)
    implementation(AndroidTestLibs.hamcrest)
    implementation(MockKLibs.mockK)
}