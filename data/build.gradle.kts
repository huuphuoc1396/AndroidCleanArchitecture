plugins {
    id(GradlePlugins.androidLib)
    id(GradlePlugins.kotlinAndroid)
    id(GradlePlugins.kotlinApt)
}

android {
    compileSdkVersion(Android.targetSdk)

    defaultConfig {
        minSdkVersion(Android.minSdk)
        targetSdkVersion(Android.targetSdk)
        versionCode = Android.versionCode
        versionName = Android.versionName

        testInstrumentationRunner = AndroidJUnitRunner.runner
    }

    buildTypes {
        getByName(BuildTypes.release) {
            isMinifyEnabled = BuildTypes.minifyRelease
            proguardFiles(BuildTypes.proguardRelease)
        }

        getByName(BuildTypes.debug) {
            isMinifyEnabled = BuildTypes.minifyDebug
            proguardFiles(BuildTypes.proguardDebug)
        }
    }

    flavorDimensions(ProductFlavors.dimensions)

    productFlavors {

        create(ProductFlavors.develop) {
            setMatchingFallbacks(listOf(BuildTypes.debug, BuildTypes.release))

            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
        }

        create(ProductFlavors.product) {
            setMatchingFallbacks(listOf(BuildTypes.release))

            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Modules.domain))
    implementation(project(Modules.coreLib))

    implementation(KotlinLibs.kotlinStdlib)
    implementation(AndroidSupportLibs.androidxCore)

    implementation(CoroutinesLibs.coroutinesCore)
    implementation(CoroutinesLibs.androidCoroutines)

    implementation(RoomLibs.roomRuntime)
    implementation(RoomLibs.roomKtx)

    implementation(KoinLibs.koinAndroid)

    implementation(TimberLibs.timber)
    implementation(OkHttp3Libs.loggingInterceptor)

    implementation(RetrofitLibs.retrofit)
    implementation(RetrofitLibs.retrofitGson)

    implementation(OkHttp3Libs.okHttp3)

    debugImplementation(ChuckerLibs.debugChucker)
    releaseImplementation(ChuckerLibs.releaseChucker)

    kapt(RoomLibs.roomCompiler)

    testImplementation(AndroidTestLibs.junit)
    testImplementation(AndroidTestLibs.androidTestJunit)
    testImplementation(AndroidTestLibs.archTestCore)
    testImplementation(AndroidTestLibs.hamcrest)
    testImplementation(MockKLibs.mockK)
    testImplementation(MockKLibs.androidMockK)
    testImplementation(OkHttp3Libs.mockWebServer)
    testImplementation(KoinLibs.koinTest)
    testImplementation(AndroidTestLibs.robolectric)
    testImplementation(RoomLibs.roomTesting)
    testImplementation(JunitDataProviderLibs.junitDataProvider)
    testImplementation(project(Modules.coreUnitTest))

    androidTestImplementation(AndroidTestLibs.androidTestJunit)
}