object Url {
    const val jitPack = "https://jitpack.io"
}

object Android {
    const val minSdk = 21
    const val targetSdk = 31
    const val applicationId = "com.example.clean_architecture"
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Modules {
    const val data = ":data"
    const val domain = ":domain"
    const val coreUnitTest = ":core:core-unit-test"
}

object BuildTypes {
    const val debug = "debug"
    const val release = "release"

    // Release
    const val minifyRelease = false
    const val isShrinkResourcesRelease = false
    const val proguardRelease = "proguard-rules.pro"

    // Debug
    const val minifyDebug = false
    const val isShrinkResourcesDebug = false
    const val proguardDebug = "proguard-rules.pro"
}

object ProductFlavors {
    const val dimensions = "default"
    const val develop = "dev"
    const val product = "prod"
}

object BuildPlugins {
    private const val androidPluginVersion = "7.0.3"
    private const val googleServicesVersion = "4.3.10"
    private const val firebaseCrashlyticsVersion = "2.8.0"

    const val android = "com.android.tools.build:gradle:${androidPluginVersion}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${KotlinLibs.version}"
    const val navigation = "androidx.navigation:navigation-safe-args-gradle-plugin:${NavigationLibs.version}"
    const val googleServices = "com.google.gms:google-services:${googleServicesVersion}"
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-gradle:${firebaseCrashlyticsVersion}"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${HiltLibs.version}"
}

object GradlePlugins {
    const val android = "com.android.application"
    const val kotlin = "kotlin"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinParcelize = "kotlin-parcelize"
    const val kotlinApt = "kotlin-kapt"
    const val javaLib = "java-library"
    const val androidLib = "com.android.library"
    const val navigation = "androidx.navigation.safeargs.kotlin"
    const val googleServices = "com.google.gms.google-services"
    const val firebaseCrashlytics = "com.google.firebase.crashlytics"
    const val hilt = "dagger.hilt.android.plugin"
}

object AndroidJUnitRunner {
    const val runner = "androidx.test.runner.AndroidJUnitRunner"
}

object AndroidTestLibs {
    private const val junitVersion = "4.13.2"
    private const val androidTestVersion = "1.4.0"
    private const val androidTestJunitVersion = "1.1.3"
    private const val archTestCoreVersion = "2.1.0"
    private const val espressoCoreVersion = "3.4.0"
    private const val hamcrestVersion = "1.3"
    private const val robolectricVersion = "4.6.1"

    // Core
    const val junit = "junit:junit:${junitVersion}"
    const val androidTestCore = "androidx.test:core:${androidTestVersion}"

    // AndroidJUnitRunner and JUnit Rules
    const val androidTestRunner = "androidx.test:runner:${androidTestVersion}"
    const val androidTestRules = "androidx.test:rules:${androidTestVersion}"

    // Assertions
    const val androidTestTruth = "androidx.test.ext:truth:${androidTestVersion}"
    const val androidTestJunit = "androidx.test.ext:junit:${androidTestJunitVersion}"

    // LiveData
    const val archTestCore = "androidx.arch.core:core-testing:${archTestCoreVersion}"

    // Espresso core
    const val espressoCore = "androidx.test.espresso:espresso-core:${espressoCoreVersion}"

    // Hamcrest
    const val hamcrest = "org.hamcrest:hamcrest-all:${hamcrestVersion}"

    // Robolectric
    const val robolectric = "org.robolectric:robolectric:${robolectricVersion}"
}

object KotlinLibs {
    internal const val version = "1.5.31"

    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${version}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${version}"
}

object LifecycleLibs {
    private const val version = "2.4.0"

    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${version}"
    const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${version}"
    const val viewModelSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${version}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${version}"
}

object AndroidSupportLibs {
    private const val androidxCoreVersion = "1.7.0"
    private const val annotationsVersion = "1.3.0"
    private const val appCompatVersion = "1.4.0"
    private const val cardViewVersion = "1.0.0"
    private const val materialVersion = "1.4.0"
    private const val constraintVersion = "2.1.2"
    private const val recyclerViewVersion = "1.2.1"
    private const val swipeRefreshLayoutVersion = "1.1.0"

    const val androidxCore = "androidx.core:core-ktx:${androidxCoreVersion}"
    const val annotations = "androidx.annotation:annotation:${annotationsVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${appCompatVersion}"
    const val cardView = "androidx.cardview:cardview:${cardViewVersion}"
    const val material = "com.google.android.material:material:${materialVersion}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${constraintVersion}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${recyclerViewVersion}"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${swipeRefreshLayoutVersion}"
}

object NavigationLibs {
    internal const val version = "2.3.5"

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${version}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${version}"
}

object RoomLibs {
    private const val version = "2.3.0"

    const val roomRuntime = "androidx.room:room-runtime:${version}"
    const val roomCompiler = "androidx.room:room-compiler:${version}"
    const val roomKtx = "androidx.room:room-ktx:${version}"
    const val roomTesting = "androidx.room:room-testing:$version"
}

object CoroutinesLibs {
    private const val version = "1.4.3"

    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${version}"
    const val androidCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${version}"
}

object FirebaseLibs {
    private const val version = "29.0.0"

    const val firebaseBom = "com.google.firebase:firebase-bom:${version}"
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
}

object RetrofitLibs {
    private const val version = "2.9.0"

    const val retrofit = "com.squareup.retrofit2:retrofit:${version}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${version}"
}

object GlideLibs {
    private const val version = "4.12.0"

    const val glide = "com.github.bumptech.glide:glide:${version}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${version}"
}

object TimberLibs {
    private const val version = "4.7.1"

    const val timber = "com.jakewharton.timber:timber:${version}"
}

object ChuckerLibs {
    private const val version = "3.5.2"

    const val debugChucker = "com.github.chuckerteam.chucker:library:${version}"
    const val releaseChucker = "com.github.chuckerteam.chucker:library-no-op:${version}"
}

object LeakCanaryLibs {
    private const val version = "2.7"

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${version}"
}

object OkHttp3Libs {
    private const val version = "4.9.2"

    const val okHttp3 = "com.squareup.okhttp3:okhttp:${version}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${version}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${version}"
}

object JunitDataProviderLibs {
    private const val version = "2.8"

    const val junitDataProvider = "com.tngtech.junit.dataprovider:junit4-dataprovider:${version}"
}

object MockKLibs {
    private const val version = "1.12.0"

    const val mockK = "io.mockk:mockk:${version}"
    const val androidMockK = "io.mockk:mockk-android:${version}"
}

object HiltLibs {
    const val version = "2.38.1"

    const val hiltCore = "com.google.dagger:hilt-core:$version"
    const val hiltAndroid = "com.google.dagger:hilt-android:$version"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:$version"
}