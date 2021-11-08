plugins {
    id(GradlePlugins.androidLib)
    id(GradlePlugins.kotlinAndroid)
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
        }

        create(ProductFlavors.product) {
            setMatchingFallbacks(listOf(BuildTypes.release))
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
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

    implementation(project(Modules.coreLib))

    implementation(KotlinLibs.kotlinStdlib)
    implementation(AndroidSupportLibs.androidxCore)
    implementation(LifecycleLibs.viewModel)
    implementation(LifecycleLibs.liveDataKtx)
    implementation(LifecycleLibs.viewModelSavedState)

    implementation(HiltLibs.hiltAndroid)

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
    implementation(AndroidSupportLibs.swipeRefreshLayout)

    implementation(TimberLibs.timber)
    implementation(GlideLibs.glide)

    kapt(LifecycleLibs.lifecycleCompiler)
    kapt(GlideLibs.glideCompiler)
    kapt(HiltLibs.hiltCompiler)
}