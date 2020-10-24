# Clean Architecture

<img src=https://github.com/huuphuoc1396/clean-architecture/blob/update_readme/app_recording.gif width="405" height="720">

## Summary
This is an Android Architecture sample written in Kotlin using [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) and [Jetpack components](https://developer.android.com/jetpack).

In this sample you'll find:
* Two **[product flavors](https://developer.android.com/studio/build/build-variants#product-flavors)**, `dev` and `prod`.
* [Androidx](https://developer.android.com/jetpack/androidx) packages fully replace the Support Library.
* [Material Components](https://material.io/develop/android) for Android.
* A single-activity architecture, using the **[Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)** to manage fragment operations.
* Reactive UIs using **[LiveData](https://developer.android.com/topic/libraries/architecture/livedata)** observables and **[Data Binding](https://developer.android.com/topic/libraries/data-binding)**.
* Kotlin **[Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)** for background operations.
* [Koin](https://insert-koin.io/) supports lightweight dependency injection.
* [Glide](https://github.com/bumptech/glide) supports the image loading.
* [Timber](https://github.com/JakeWharton/timber) - A logger with a small, extensible API which provides utility on top of Android's normal Log class.
* [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
* An [OkHttp interceptor](https://square.github.io/okhttp/interceptors/) which logs HTTP request and response data.
* [Chucker](https://github.com/ChuckerTeam/chucker) simplifies the inspection of HTTP(S) requests/responses, and Throwables fired by your Android App.
* [LeakCanary](https://github.com/square/leakcanary) - A memory leak detection library for Android.
* [Mockk](https://mockk.io/) supports mocking for Kotlin testing.
* [Local unit tests](https://developer.android.com/training/testing/unit-testing/local-unit-tests) evaluates your app's logic
* [Robolectric](http://robolectric.org/getting-started/) supports running in a simulated Android environment inside a JVM.
* [JaCoCo](https://www.eclemma.org/jacoco/) generates an aggregated Jacoco test coverage report for all sub-projects.

## Structure
<img src=https://github.com/huuphuoc1396/clean-architecture/blob/update_readme/clean-architecture-structure.png width="800" height="450">

* Presentation: Views (Fragments or Activities) interact with ViewModels which use the use cases to access the business logic.
* Domain: Holds all business logic and the use cases represent all the possible actions a developer can perform from the presentation layer.
* Data: Including the repository implements to access local or remote data

## References
* [Android Architecture Blueprints - Use Cases/Interactors in Domain layer](https://github.com/android/architecture-samples/tree/usecases)
* [The Clean Architecture concept](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
* [Kotlin coroutines on Android](https://developer.android.com/kotlin/coroutines)