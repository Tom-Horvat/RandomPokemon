plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:${libs.versions.agp.get()}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${libs.versions.kotlin.get()}")
    implementation("androidx.room:room-gradle-plugin:${libs.versions.room.get()}")
}

gradlePlugin {
    plugins {
        register("randompokemon.android.library") {
            id = "randompokemon.android.library"
            implementationClass = "RandomPokemonAndroidLibraryConventionPlugin"
        }

        register("randompokemon.android.application") {
            id = "randompokemon.android.application"
            implementationClass = "RandomPokemonAndroidApplicationConventionPlugin"
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