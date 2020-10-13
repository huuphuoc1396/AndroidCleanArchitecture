plugins {
    id(GradlePlugins.android)
    kotlin(GradlePlugins.kotlinAndroid)
    kotlin(GradlePlugins.kotlinAndroidExt)
    kotlin(GradlePlugins.kotlinApt)
}

apply {
    plugin(GradlePlugins.navigation)
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
            proguardFiles(BuildType.proguardRelease)
        }

        getByName(BuildType.debug) {
            isMinifyEnabled = BuildType.minifyDebug
            proguardFiles(BuildType.proguardDebug)
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
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Modules.data))
    implementation(project(Modules.domain))
    implementation(project(Modules.commonLib))

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

    testImplementation(TestLibs.junit)
    testImplementation(TestLibs.hamcrest)
    testImplementation(TestLibs.archTestCore)
    testImplementation(TestLibs.mockk)
    testImplementation(TestLibs.androidMockk)
    testImplementation(TestLibs.robolectric)
    testImplementation(TestLibs.koin)

    androidTestImplementation(TestLibs.androidTestJunit)
    androidTestImplementation(TestLibs.espressoCore)
}