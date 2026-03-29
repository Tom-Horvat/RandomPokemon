# Random Pokemon

This is a demo project used to explore and experiment with various Android features and concepts. It is a multi-module project that uses the public [PokéAPI](https://pokeapi.co/) as a remote data source to display random Pokémon.

## Project Structure

The project is divided into several modules:

*   `app`: The main application module.
*   `core`: Core functionalities and utilities shared across the project.
*   `commons`: Common UI components and resources.
*   `landing`: The landing screen of the application.
*   `navigation`: Navigation logic for the application.
*   `build-logic`: Contains custom build logic for the project.

## Features

*   Fetches and displays a random Pokémon from the PokéAPI.
*   Demonstrates a multi-module architecture.
*   Utilizes modern Android development practices.

## Technologies Used

*   Kotlin
*   Jetpack Compose
*   Coroutines
*   Retrofit
*   Gradle with Kotlin DSL

## How to Build

To build the project, open it in Android Studio and sync the Gradle files. Then, you can build and run the `app` module on an emulator or a physical device.
