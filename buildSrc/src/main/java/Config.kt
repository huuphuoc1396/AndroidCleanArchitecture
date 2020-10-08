object Versions {
    internal const val kotlin = "1.4.10"
    internal const val androidPlugin = "4.0.2"
    internal const val androidxCore = "1.3.2"
    internal const val archCore = "2.1.0"
    internal const val room = "2.2.5"
    internal const val lifecycle = "2.2.0"
    internal const val lifecycleSaved = "1.0.0"
    internal const val support = "1.2.0"
    internal const val recyclerView = "1.1.0"
    internal const val recyclerViewSelection = "1.0.0"
    internal const val cardView = "1.0.0"
    internal const val legacySupportV4 = "1.0.0"
    internal const val material = "1.2.1"
    internal const val glide = "4.11.0"
    internal const val androidJunit = "1.1.2"
    internal const val mockk = "1.10.0"
    internal const val hamcrest = "1.3"
    internal const val mockWebServer = "3.8.1"
    internal const val robolectric = "4.4"
    internal const val retrofit = "2.9.0"
    internal const val loggingInterceptor = "4.9.0"
    internal const val constraintLayout = "2.0.2"
    internal const val timber = "4.7.1"
    internal const val easyPermission = "3.0.0"
    internal const val navSafePlugin = "2.3.0"
    internal const val fragmentKtx = "1.2.5"
    internal const val espressoCore = "3.3.0"
    internal const val junit = "4.12"
}

object Url {
    const val fabric = "https://maven.fabric.io/public"
    const val jitPack = "https://jitpack.io"
}

object BuildPlugins {
    const val androidPlugin = "com.android.tools.build:gradle:${Versions.androidPlugin}"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Android {
    const val minSdk = 21
    const val targetSdk = 30
    const val applicationId = "com.example.sampleproject"
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

    const val minifyRelease = false
    const val proguardRelease = "proguard-rules.pro"

    const val minifyDebug = false
    const val proguardDebug = "proguard-rules.pro"
}

object Libs {
    // Core
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"
    const val archTesting = "androidx.arch.core:core-testing:${Versions.archCore}"
    const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

    // Support libs
    const val annotations = "androidx.annotation:annotation:${Versions.support}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.support}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val recyclerviewSelection = "androidx.recyclerview:recyclerview-selection:${Versions.recyclerViewSelection}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val legacySupportV4 = "androidx.legacy:legacy-support-v4:${Versions.legacySupportV4}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"

    // ConstraintLayout
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    // Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    // Lifecycle
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle}"
    const val lifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycle}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycleJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val lifecycleSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycleSaved}"

    // Retrofit
    const val retrofitRuntime = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

    // Navigation
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navSafePlugin}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navSafePlugin}"

    // Glide
    const val glideRuntime = "com.github.bumptech.glide:glide:${Versions.glide}"

    // Permission
    const val easyPermissions = "pub.devrel:easypermissions:${Versions.easyPermission}"

    // Timber
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    // JUnit
    const val junit = "junit:junit:${Versions.junit}"
    const val androidJunit = "androidx.test.ext:junit:${Versions.androidJunit}"

    // Testing
    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    const val kotlinAllOpen = "org.jetbrains.kotlin:kotlin-allopen:${Versions.kotlin}"
    const val hamcrest = "org.hamcrest:hamcrest-all:${Versions.hamcrest}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val androidMockk = "io.mockk:mockk-android:${Versions.mockk}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServer}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}
