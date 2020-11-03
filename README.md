# Clean Architecture

<img src=https://github.com/huuphuoc1396/clean-architecture/blob/develop/images/app-recording.gif width="405" height="720">

## Summary
This is an Android Architecture sample written in Kotlin using [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) and [Jetpack components](https://developer.android.com/jetpack).

In this sample you'll find:
* Two [product flavors](https://developer.android.com/studio/build/build-variants#product-flavors), `dev` and `prod`.
* [Androidx](https://developer.android.com/jetpack/androidx) packages fully replace the Support Library.
* [Material Components](https://material.io/develop/android) for Android.
* A single-activity architecture, using the [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started) to manage fragment operations.
* Reactive UIs using [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) observables and [Data Binding](https://developer.android.com/topic/libraries/data-binding).
* Kotlin [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) for background operations.
* [Koin](https://insert-koin.io/) supports lightweight dependency injection.
* [Glide](https://github.com/bumptech/glide) supports the image loading.
* [Timber](https://github.com/JakeWharton/timber) - A logger with a small, extensible API which provides utility on top of Android's normal Log class.
* [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
* An [OkHttp interceptor](https://square.github.io/okhttp/interceptors/) which logs HTTP request and response data.
* [Chucker](https://github.com/ChuckerTeam/chucker) simplifies the inspection of HTTP(S) requests/responses, and Throwables fired by your Android App.
* [LeakCanary](https://github.com/square/leakcanary) - A memory leak detection library for Android.
* [Mockk](https://mockk.io/) supports mocking for Kotlin testing.
* [Local unit tests](https://developer.android.com/training/testing/unit-testing/local-unit-tests) evaluate your app's logic more quickly and don't need the fidelity and confidence associated with running tests on a real device.
* [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) - A scriptable web server for testing HTTP clients
* [Robolectric](http://robolectric.org/getting-started/) supports running in a simulated Android environment inside a JVM.
* [JaCoCo](https://www.eclemma.org/jacoco/) generates an aggregated JaCoCo test coverage report for all sub-projects.

## Structure
<img src=https://github.com/huuphuoc1396/clean-architecture/blob/develop/images/clean-architecture-structure.png width="800" height="320">

* **Presentation**: The **Views** (Fragments or Activities) will interact with **ViewModels** which access the **business logic** through **use cases**.
* **Domain**: Holds all **business logic** and **use cases** represent all the possible actions able to being performed by the **presentation** module.
* **Data**: Contains all **repository implements** to access **local** or **remote** data

## Testing
The project uses local unit tests that run on your computer. To run it and generate a coverage report, you can run:
`./gradlew jacocoFullReport`

You can see this report at: `./build/reports/jacoco/html/index.html`
<img src=https://github.com/huuphuoc1396/clean-architecture/blob/develop/images/jacoco-full-report.png width="800" height="314">

You can easily write Unit Test up to 70% code coverage lines of code (LOC), if you write focus on
* **Presentation**: `ViewModel`, `Mapper`
* **Domain**: `UseCase`, `Repository`, `Exception` handlers
* **Data**: API service, local (database, shared preferences), `RepositoryImpl`, `Mapper`

## References
* [Android Architecture Blueprints - Use Cases/Interactors in Domain layer](https://github.com/android/architecture-samples/tree/usecases)
* [The Clean Architecture concept](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
* [Kotlin coroutines on Android](https://developer.android.com/kotlin/coroutines)