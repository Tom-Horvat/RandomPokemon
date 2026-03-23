plugins {
    id("randompokemon.android.library")
}

android {
    namespace = "io.bitbot.bemusedbaboon.navigation"
}

dependencies {
    implementation(libs.voyager.navigator)
}