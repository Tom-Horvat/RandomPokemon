plugins {
    id("randompokemon.android.library")
    id("randompokemon.android.compose")
}

libraryNamespace("landing")

dependencies {

    implementation(project(":core"))
    implementation(project(":commons"))
    implementation(project(":navigation"))

    implementation(libs.timber)

    implementation(libs.koin)
    implementation(libs.koin.compose)

    implementation(libs.voyager.navigator)

    implementation(libs.coil.compose)
    implementation(libs.coil.network)
}
