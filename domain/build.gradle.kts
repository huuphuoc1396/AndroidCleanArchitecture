import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(GradlePlugins.javaLib)
    id(GradlePlugins.kotlin)
}

project.tasks.withType(KotlinCompile::class.java).configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Modules.coreLib))

    implementation(KotlinLibs.kotlinStdlib)

    implementation(CoroutinesLibs.coroutinesCore)

    implementation(KoinLibs.koinCore)

    implementation(TimberLibs.timber)

    testImplementation(AndroidTestLibs.junit)
    testImplementation(AndroidTestLibs.androidTestJunit)
    testImplementation(AndroidTestLibs.archTestCore)
    testImplementation(AndroidTestLibs.hamcrest)
    testImplementation(MockKLibs.mockK)
    testImplementation(KoinLibs.koinTest)
    testImplementation(project(Modules.coreUnitTest))
}