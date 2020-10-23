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
            isShrinkResources = BuildType.isShrinkResourcesRelease
            proguardFiles(BuildType.proguardRelease)
        }

        getByName(BuildType.debug) {
            isMinifyEnabled = BuildType.minifyDebug
            isShrinkResources = BuildType.isShrinkResourcesDebug
            proguardFiles(BuildType.proguardDebug)
        }
    }

    flavorDimensions(ProductFlavors.dimensions)

    productFlavors {

        create(ProductFlavors.develop) {
            matchingFallbacks = listOf(BuildType.debug, BuildType.release)

            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
        }

        create(ProductFlavors.product) {
            matchingFallbacks = listOf(BuildType.release)

            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
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

    implementation(Libs.okHttp3)

    debugImplementation(Libs.debugChucker)
    releaseImplementation(Libs.releaseChucker)

    kapt(Libs.roomCompiler)

    testImplementation(TestLibs.junit)
    testImplementation(TestLibs.androidTestJunit)
    testImplementation(TestLibs.archTestCore)
    testImplementation(TestLibs.hamcrest)
    testImplementation(TestLibs.mockk)
    testImplementation(TestLibs.androidMockk)
    testImplementation(TestLibs.mockWebServer)
    testImplementation(TestLibs.koin)
    testImplementation(TestLibs.robolectric)
    testImplementation(TestLibs.room)
    testImplementation(TestLibs.junitDataProvider)
    testImplementation(project(Modules.commonUnitTest))

    androidTestImplementation(TestLibs.androidTestJunit)
}