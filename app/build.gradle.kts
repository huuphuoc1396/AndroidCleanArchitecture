import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import java.text.SimpleDateFormat
import java.util.*

plugins {
    id(GradlePlugins.android)
    id(GradlePlugins.kotlinAndroid)
    id(GradlePlugins.kotlinParcelize)
    id(GradlePlugins.kotlinApt)
    id(GradlePlugins.navigation)
    id(GradlePlugins.googleServices)
    id(GradlePlugins.firebaseCrashlytics)
    id(GradlePlugins.hilt)
}

android {
    compileSdk = Android.targetSdk

    defaultConfig {
        applicationId = Android.applicationId
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = Android.versionCode
        versionName = Android.versionName

        testInstrumentationRunner = AndroidJUnitRunner.runner
    }

    signingConfigs {
        create(BuildTypes.release) {
            storeFile = file("../debug-keystore.jks")
            storePassword = "123456"
            keyAlias = "key0"
            keyPassword = "123456"
        }
    }

    buildTypes {
        getByName(BuildTypes.release) {
            isMinifyEnabled = BuildTypes.minifyRelease
            isShrinkResources = BuildTypes.isShrinkResourcesRelease
            proguardFiles(BuildTypes.proguardRelease)

            signingConfig = signingConfigs.getByName(BuildTypes.release)
        }

        getByName(BuildTypes.debug) {
            isMinifyEnabled = BuildTypes.minifyDebug
            isShrinkResources = BuildTypes.isShrinkResourcesDebug
            proguardFiles(BuildTypes.proguardDebug)
        }
    }

    flavorDimensions += ProductFlavors.dimensions

    productFlavors {
        val applicationName = "applicationName"

        create(ProductFlavors.develop) {
            applicationIdSuffix = ".dev"
            manifestPlaceholders[applicationName] = "[DEV] Clean Architecture"
            matchingFallbacks += listOf(BuildTypes.debug, BuildTypes.release)
        }

        create(ProductFlavors.product) {
            manifestPlaceholders[applicationName] = "@string/app_name"
            matchingFallbacks += listOf(BuildTypes.release)
        }
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    applicationVariants.all {
        val outputFileName = "CleanArchitecture" +
                "_${name}" +
                "_verCode${Android.versionCode}" +
                "_${SimpleDateFormat("yyMdHms", Locale.US).format(Date())}.apk"
        outputs.all {
            val output = this as? BaseVariantOutputImpl
            output?.outputFileName = outputFileName
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Modules.data))
    implementation(project(Modules.domain))

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

    implementation(platform(FirebaseLibs.firebaseBom))
    implementation(FirebaseLibs.firebaseCrashlytics)
    implementation(FirebaseLibs.firebaseAnalytics)

    implementation(TimberLibs.timber)
    implementation(GlideLibs.glide)

    debugImplementation(LeakCanaryLibs.leakCanary)

    kapt(LifecycleLibs.lifecycleCompiler)
    kapt(GlideLibs.glideCompiler)
    kapt(HiltLibs.hiltCompiler)

    testImplementation(AndroidTestLibs.junit)
    testImplementation(AndroidTestLibs.androidTestJunit)
    testImplementation(AndroidTestLibs.archTestCore)
    testImplementation(AndroidTestLibs.hamcrest)
    testImplementation(MockKLibs.mockK)
    testImplementation(MockKLibs.androidMockK)
    testImplementation(AndroidTestLibs.robolectric)
    testImplementation(JunitDataProviderLibs.junitDataProvider)

    androidTestImplementation(AndroidTestLibs.androidTestJunit)
    androidTestImplementation(AndroidTestLibs.espressoCore)
}