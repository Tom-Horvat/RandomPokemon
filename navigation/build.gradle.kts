plugins {
    id("randompokemon.android.library")
    id("randompokemon.kotlin.android")
}

android {
    namespace = "io.bitbot.bemusedbaboon.navigation"
}

dependencies {
    implementation(libs.voyager.navigator)
}