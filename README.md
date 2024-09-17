# StockWalls 👀 Images via Unsplash API 🖼

StockWalls is an Android application designed to showcase stunning images sourced from the Unsplash API. The app is built using modern Android libraries and follows the **MVVM architecture** to ensure clean, maintainable, and scalable code. It leverages **Dagger Hilt** for dependency injection, **Kotlin Coroutines** for asynchronous operations, and a suite of other libraries to enhance functionality and user experience. Recently, **pagination** has been added using **Jetpack Paging 3** to load images efficiently as the user scrolls.

## Application Screens 📸

Explore the app's interface through this demo video:

<img src="https://github.com/abhishekdubey331/StockWalls/blob/main/demo-video.gif" width="500"/>

## API
- **Unsplash API**: [Explore Unsplash API](https://unsplash.com/developers)

## Libraries & Tools 🛠 ⚙️

- **[Kotlin](https://github.com/JetBrains/kotlin)**: The primary programming language used in the app.
- **[Retrofit](https://github.com/square/retrofit)**: A type-safe HTTP client for Android and JVM, used for making network requests.
- **[OkHttp](https://github.com/square/okhttp)**: A robust HTTP client for the JVM and Android, used in conjunction with Retrofit.
- **[Coroutines](https://github.com/Kotlin/kotlinx.coroutines)**: Manages asynchronous operations and ensures smooth execution of background tasks.
- **[Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle)**: Handles lifecycle-aware components to perform actions in response to lifecycle changes.
- **[Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android)**: Simplifies dependency injection, reducing boilerplate code and enhancing testability.
- **[LiveData](https://developer.android.com/topic/libraries/architecture/livedata)**: An observable data holder class that ensures data updates are handled properly across the UI.
- **[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)**: Manages and stores UI-related data in a lifecycle-aware manner, improving data persistence and handling.
- **[Coil](https://github.com/coil-kt/coil)**: An image loading library that efficiently handles image loading and caching with Kotlin Coroutines.
- **[Paging 3](https://developer.android.com/topic/libraries/architecture/paging)**: A library to help load and display pages of data from a large dataset, adding pagination support to the photo list view.

## Features 📝

- **MVVM Architecture**: Ensures a clean separation of concerns and better code organization.
- **Dependency Injection with Hilt**: Simplifies dependency injection and reduces boilerplate code.
- **Paging with Jetpack Paging 3**: Enables efficient loading of photos as the user scrolls, improving performance and user experience.
- **LiveData & ViewModel**: Manages UI-related data and handles lifecycle changes effectively.
- **Asynchronous Operations**: Kotlin Coroutines handle background tasks smoothly without blocking the main thread.
- **Image Loading with Coil**: Loads images asynchronously and handles image caching efficiently.

## How to Run 🚀

1. Clone this repository:
   ```bash
   git clone https://github.com/abhishekdubey331/StockWalls.git
