object Versions {
    internal const val androidPlugin = "4.0.2"
    internal const val kotlin = "1.4.10"

    internal const val androidxCore = "1.3.2"
    internal const val lifecycle = "2.2.0"
    internal const val navigation = "2.3.0"
    internal const val room = "2.2.5"
    internal const val koin = "2.2.0-rc-2"
    internal const val coroutines = "1.3.9"

    // Support
    internal const val annotations = "1.1.0"
    internal const val appCompat = "1.2.0"
    internal const val cardView = "1.0.0"
    internal const val constraint = "2.0.2"
    internal const val material = "1.2.1"
    internal const val recyclerView = "1.1.0"

    // Testing
    internal const val junit = "4.12"
    internal const val androidTest = "1.3.0"
    internal const val androidTestJunit = "1.1.2"
    internal const val archTestCore = "2.1.0"
    internal const val espressoCore = "3.3.0"
    internal const val hamcrest = "1.3"
    internal const val mockk = "1.10.2"
    internal const val mockWebServer = "4.9.0"
    internal const val robolectric = "4.4"

    // Logging
    internal const val loggingInterceptor = "4.9.0"
    internal const val timber = "4.7.1"

    // Third-party
    internal const val retrofit = "2.9.0"
    internal const val glide = "4.11.0"
    internal const val easyPermission = "3.0.0"
    internal const val chucker = "3.3.0"
}

object Url {
    const val fabric = "https://maven.fabric.io/public"
    const val jitPack = "https://jitpack.io"
}

object BuildPlugins {
    const val androidPlugin = "com.android.tools.build:gradle:${Versions.androidPlugin}"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val navigationPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}

object Android {
    const val minSdk = 21
    const val targetSdk = 30
    const val applicationId = "com.example.presentation"
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object GradlePlugins {
    const val android = "com.android.application"
    const val kotlin = "kotlin"
    const val kotlinAndroid = "android"
    const val kotlinAndroidExt = "android.extensions"
    const val kotlinApt = "kapt"
    const val javaLib = "java-library"
    const val androidLib = "com.android.library"
    const val navigation = "androidx.navigation.safeargs.kotlin"
}

object Modules {
    const val app = ":app"
    const val data = ":data"
    const val domain = ":domain"
    const val commonLib = ":common-lib"
}

object AndroidJUnit {
    const val runner = "androidx.test.runner.AndroidJUnitRunner"
}

object BuildType {
    const val debug = "debug"
    const val release = "release"

    // Release
    const val minifyRelease = false
    const val proguardRelease = "proguard-rules.pro"

    // Debug
    const val minifyDebug = false
    const val proguardDebug = "proguard-rules.pro"
}

object TestLibs {

    // Core
    const val junit = "junit:junit:${Versions.junit}"
    const val androidTestCore = "androidx.test:core:${Versions.androidTest}"

    // AndroidJUnitRunner and JUnit Rules
    const val androidTestRunner = "androidx.test:runner:${Versions.androidTest}"
    const val androidTestRules = "androidx.test:rules:${Versions.androidTest}"

    // Assertions
    const val androidTestTruth = "androidx.test.ext:truth:${Versions.androidTest}"
    const val androidTestJunit = "androidx.test.ext:junit:${Versions.androidTestJunit}"

    // LiveData
    const val archTestCore = "androidx.arch.core:core-testing:${Versions.archTestCore}"

    // Espresso core
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"

    // Hamcrest
    const val hamcrest = "org.hamcrest:hamcrest-all:${Versions.hamcrest}"

    // Mocking
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val androidMockk = "io.mockk:mockk-android:${Versions.mockk}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServer}"

    // Robolectric
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"

    // Room
    const val room = "androidx.room:room-testing:${Versions.room}"

    // Koin
    const val koin = "org.koin:koin-test:${Versions.koin}"
}

object Libs {
    // Core
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"

    // Lifecycle
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycle}"
    const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val viewModelSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    // Navigation
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    // Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    // Koin
    const val koinCore = "org.koin:koin-core:${Versions.koin}"
    const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    const val koinAndroidScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val koinFragment = "org.koin:koin-androidx-fragment:${Versions.koin}"

    // Coroutines
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val androidCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // Support
    const val annotations = "androidx.annotation:annotation:${Versions.annotations}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"

    // Logging
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    // Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    // Permission
    const val easyPermissions = "pub.devrel:easypermissions:${Versions.easyPermission}"

    const val debugChucker = "com.github.chuckerteam.chucker:library:${Versions.chucker}"
    const val releaseChucker = "com.github.chuckerteam.chucker:library-no-op:${Versions.chucker}"
}
