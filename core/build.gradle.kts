plugins {
    id("randompokemon.android.library")
    id("randompokemon.kotlin.android")
    id("randompokemon.android.room")
}

android {
    namespace = "io.bitbot.bemusedbaboon.core"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
