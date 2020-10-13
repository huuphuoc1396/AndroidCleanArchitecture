plugins {
    id(GradlePlugins.androidLib)
    kotlin(GradlePlugins.kotlinAndroid)
    kotlin(GradlePlugins.kotlinAndroidExt)
    kotlin(GradlePlugins.kotlinApt)
}

android {
    compileSdkVersion(Android.targetSdk)

    defaultConfig {
        minSdkVersion(Android.minSdk)
        targetSdkVersion(Android.targetSdk)
        versionCode = Android.versionCode
        versionName = Android.versionName

        testInstrumentationRunner = AndroidJUnit.runner
    }

    buildTypes {
        getByName(BuildType.release) {
            isMinifyEnabled = BuildType.minifyRelease
            proguardFiles(BuildType.proguardRelease)
        }

        getByName(BuildType.debug) {
            isMinifyEnabled = BuildType.minifyDebug
            proguardFiles(BuildType.proguardDebug)
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Modules.domain))
    implementation(project(Modules.commonLib))

    implementation(Libs.kotlinStdlib)
    implementation(Libs.androidxCore)

    implementation(Libs.coroutinesCore)
    implementation(Libs.androidCoroutines)

    implementation(Libs.roomRuntime)
    implementation(Libs.roomKtx)

    implementation(Libs.koinAndroid)

    implementation(Libs.timber)
    implementation(Libs.loggingInterceptor)

    implementation(Libs.retrofit)
    implementation(Libs.retrofitGson)

    kapt(Libs.roomCompiler)

    testImplementation(TestLibs.junit)
    testImplementation(TestLibs.hamcrest)
    testImplementation(TestLibs.mockk)
    testImplementation(TestLibs.androidMockk)
    testImplementation(TestLibs.mockWebServer)
    testImplementation(TestLibs.robolectric)
    testImplementation(TestLibs.room)
    testImplementation(TestLibs.koin)

    androidTestImplementation(TestLibs.androidTestJunit)
}