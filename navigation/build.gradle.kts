plugins {
    id("randompokemon.android.library")
}

libraryNamespace("navigation")

dependencies {
    implementation(libs.voyager.navigator)
}