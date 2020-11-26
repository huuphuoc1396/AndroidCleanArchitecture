plugins {
    id(GradlePlugins.android)
    id(GradlePlugins.kotlinAndroid)
    id(GradlePlugins.kotlinAndroidExt)
    id(GradlePlugins.kotlinApt)
    id(GradlePlugins.navigation)
    id(GradlePlugins.googleServices)
    id(GradlePlugins.firebaseCrashlytics)
}

android {
    compileSdkVersion(Android.targetSdk)

    defaultConfig {
        applicationId = Android.applicationId
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
        val applicationName = "applicationName"

        create(ProductFlavors.develop) {
            setMatchingFallbacks(listOf(BuildType.debug, BuildType.release))
            applicationIdSuffix = ".dev"
            setManifestPlaceholders(mapOf(applicationName to "[DEV] Clean Architecture"))
        }

        create(ProductFlavors.product) {
            setMatchingFallbacks(listOf(BuildType.release))
            setManifestPlaceholders(mapOf(applicationName to "@string/app_name"))
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
    }

    androidExtensions {
        isExperimental = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Modules.data))
    implementation(project(Modules.domain))
    implementation(project(Modules.coreLib))
    implementation(project(Modules.coreAndroid))

    implementation(Libs.kotlinStdlib)
    implementation(Libs.androidxCore)
    implementation(Libs.viewModel)
    implementation(Libs.liveDataKtx)
    implementation(Libs.viewModelSavedState)

    implementation(Libs.koinAndroid)
    implementation(Libs.koinAndroidScope)
    implementation(Libs.koinViewModel)
    implementation(Libs.koinFragment)

    implementation(Libs.coroutinesCore)
    implementation(Libs.androidCoroutines)

    implementation(Libs.navigationFragment)
    implementation(Libs.navigationUi)

    implementation(Libs.annotations)
    implementation(Libs.appCompat)
    implementation(Libs.constraint)
    implementation(Libs.cardView)
    implementation(Libs.material)
    implementation(Libs.recyclerView)

    implementation(platform(Libs.firebaseBom))
    implementation(Libs.firebaseCrashlytics)
    implementation(Libs.firebaseAnalytics)

    implementation(Libs.timber)
    implementation(Libs.glide)
    implementation(Libs.easyPermissions)

    debugImplementation(Libs.leakCanary)

    kapt(Libs.lifecycleCompiler)
    kapt(Libs.glideCompiler)

    testImplementation(TestLibs.junit)
    testImplementation(TestLibs.androidTestJunit)
    testImplementation(TestLibs.archTestCore)
    testImplementation(TestLibs.hamcrest)
    testImplementation(TestLibs.mockk)
    testImplementation(TestLibs.androidMockk)
    testImplementation(TestLibs.koin)
    testImplementation(TestLibs.robolectric)
    testImplementation(TestLibs.junitDataProvider)
    testImplementation(project(Modules.coreUnitTest))

    androidTestImplementation(TestLibs.androidTestJunit)
    androidTestImplementation(TestLibs.espressoCore)
}