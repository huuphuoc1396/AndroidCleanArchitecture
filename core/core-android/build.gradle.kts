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

    flavorDimensions(ProductFlavors.dimensions)

    productFlavors {
        create(ProductFlavors.develop) {
            setMatchingFallbacks(listOf(BuildType.debug, BuildType.release))
        }

        create(ProductFlavors.product) {
            setMatchingFallbacks(listOf(BuildType.release))
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

    implementation(project(Modules.coreLib))

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

    implementation(Libs.timber)
    implementation(Libs.glide)
    implementation(Libs.easyPermissions)

    kapt(Libs.lifecycleCompiler)
    kapt(Libs.glideCompiler)
}