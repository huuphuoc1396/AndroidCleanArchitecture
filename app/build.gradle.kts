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
    compileSdkVersion(Android.targetSdk)

    defaultConfig {
        applicationId = Android.applicationId
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
        val applicationName = "applicationName"

        create(ProductFlavors.develop) {
            setMatchingFallbacks(listOf(BuildTypes.debug, BuildTypes.release))
            applicationIdSuffix = ".dev"
            setManifestPlaceholders(mapOf(applicationName to "[DEV] Clean Architecture"))
        }

        create(ProductFlavors.product) {
            setMatchingFallbacks(listOf(BuildTypes.release))
            setManifestPlaceholders(mapOf(applicationName to "@string/app_name"))
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
    implementation(project(Modules.coreLib))
    implementation(project(Modules.coreAndroid))

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
    testImplementation(project(Modules.coreUnitTest))

    androidTestImplementation(AndroidTestLibs.androidTestJunit)
    androidTestImplementation(AndroidTestLibs.espressoCore)
}