plugins {
    id(GradlePlugins.androidLib)
    id(GradlePlugins.kotlinAndroid)
    id(GradlePlugins.kotlinAndroidExt)
    id(GradlePlugins.kotlinApt)
    id(GradlePlugins.navigation)
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
            isShrinkResources = BuildTypes.isShrinkResourcesRelease
            proguardFiles(BuildTypes.proguardRelease)
        }

        getByName(BuildTypes.debug) {
            isMinifyEnabled = BuildTypes.minifyDebug
            isShrinkResources = BuildTypes.isShrinkResourcesDebug
            proguardFiles(BuildTypes.proguardDebug)
        }
    }

    flavorDimensions(ProductFlavors.dimensions)

    productFlavors {

        create(ProductFlavors.develop) {
            setMatchingFallbacks(listOf(BuildTypes.debug, BuildTypes.release))
        }

        create(ProductFlavors.product) {
            setMatchingFallbacks(listOf(BuildTypes.release))
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

    implementation(project(Modules.domain))
    implementation(project(Modules.coreLib))
    implementation(project(Modules.coreAndroid))

    implementation(KotlinLibs.kotlinStdlib)
    implementation(AndroidSupportLibs.androidxCore)
    implementation(LifecycleLibs.viewModel)
    implementation(LifecycleLibs.liveDataKtx)
    implementation(LifecycleLibs.viewModelSavedState)

    implementation(KoinLibs.koinAndroid)
    implementation(KoinLibs.koinAndroidScope)
    implementation(KoinLibs.koinViewModel)
    implementation(KoinLibs.koinFragment)

    implementation(CoroutinesLibs.coroutinesCore)
    implementation(CoroutinesLibs.androidCoroutines)

    implementation(NavigationLibs.navigationFragment)
    implementation(NavigationLibs.navigationUi)

    implementation(AndroidSupportLibs.annotations)
    implementation(AndroidSupportLibs.appCompat)
    implementation(AndroidSupportLibs.constraint)
    implementation(AndroidSupportLibs.cardView)
    implementation(AndroidSupportLibs.material)
    implementation(AndroidSupportLibs.recyclerView)

    implementation(platform(FirebaseLibs.firebaseBom))
    implementation(FirebaseLibs.firebaseCrashlytics)
    implementation(FirebaseLibs.firebaseAnalytics)

    implementation(TimberLibs.timber)
    implementation(GlideLibs.glide)
    implementation(EasyPermissionsLibs.easyPermissions)

    kapt(LifecycleLibs.lifecycleCompiler)
    kapt(GlideLibs.glideCompiler)

    testImplementation(AndroidTestLibs.junit)
    testImplementation(AndroidTestLibs.androidTestJunit)
    testImplementation(AndroidTestLibs.archTestCore)
    testImplementation(AndroidTestLibs.hamcrest)
    testImplementation(MockKLibs.mockK)
    testImplementation(MockKLibs.androidMockK)
    testImplementation(KoinLibs.koinTest)
    testImplementation(AndroidTestLibs.robolectric)
    testImplementation(JunitDataProviderLibs.junitDataProvider)
    testImplementation(project(Modules.coreUnitTest))

    androidTestImplementation(AndroidTestLibs.androidTestJunit)
    androidTestImplementation(AndroidTestLibs.espressoCore)
}