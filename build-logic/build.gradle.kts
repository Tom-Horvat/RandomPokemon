plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradle)
    compileOnly(libs.kotlin.gradle)
    compileOnly(libs.room.gradle)
}

gradlePlugin {
    plugins {
        register("randompokemon.android.application") {
            id = "randompokemon.android.application"
            implementationClass = "RandomPokemonAndroidApplicationConventionPlugin"
        }

        register("randompokemon.android.library") {
            id = "randompokemon.android.library"
            implementationClass = "RandomPokemonAndroidLibraryConventionPlugin"
        }

        register("randompokemon.android.compose") {
            id = "randompokemon.android.compose"
            implementationClass = "RandomPokemonAndroidComposeConventionPlugin"
        }

        register("randompokemon.android.room") {
            id = "randompokemon.android.room"
            implementationClass = "RandomPokemonAndroidRoomConventionPlugin"
        }
    }
}