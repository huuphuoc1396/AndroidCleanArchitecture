plugins {
    id(GradlePlugins.androidLib)
    id(GradlePlugins.kotlinAndroid)
    id(GradlePlugins.kotlinApt)
}

android {
    compileSdk = Android.targetSdk

    defaultConfig {
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk

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

    flavorDimensions += ProductFlavors.dimensions

    productFlavors {

        create(ProductFlavors.develop) {
            matchingFallbacks += listOf(BuildTypes.debug, BuildTypes.release)

            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
        }

        create(ProductFlavors.product) {
            matchingFallbacks += listOf(BuildTypes.release)

            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Modules.domain))

    implementation(KotlinLibs.kotlinStdlib)
    implementation(AndroidSupportLibs.androidxCore)

    implementation(CoroutinesLibs.coroutinesCore)
    implementation(CoroutinesLibs.androidCoroutines)

    implementation(DataStoreLibs.dataStore)
    implementation(DataStoreLibs.dataStorePreferences)

    implementation(RoomLibs.roomRuntime)
    implementation(RoomLibs.roomKtx)

    implementation(HiltLibs.hiltAndroid)

    implementation(TimberLibs.timber)
    implementation(OkHttp3Libs.loggingInterceptor)

    implementation(RetrofitLibs.retrofit)
    implementation(RetrofitLibs.retrofitGson)

    implementation(OkHttp3Libs.okHttp3)

    debugImplementation(ChuckerLibs.debugChucker)
    releaseImplementation(ChuckerLibs.releaseChucker)

    kapt(RoomLibs.roomCompiler)
    kapt(HiltLibs.hiltCompiler)

    testImplementation(AndroidTestLibs.junit)
    testImplementation(AndroidTestLibs.androidTestJunit)
    testImplementation(AndroidTestLibs.archTestCore)
    testImplementation(AndroidTestLibs.hamcrest)
    testImplementation(MockKLibs.mockK)
    testImplementation(MockKLibs.androidMockK)
    testImplementation(OkHttp3Libs.mockWebServer)
    testImplementation(AndroidTestLibs.robolectric)
    testImplementation(RoomLibs.roomTesting)
    testImplementation(JunitDataProviderLibs.junitDataProvider)

    androidTestImplementation(AndroidTestLibs.androidTestJunit)
}