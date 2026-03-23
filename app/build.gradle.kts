plugins {
    id("randompokemon.android.application")
    id("randompokemon.android.compose")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":common"))
    implementation(project(":navigation"))
    implementation(project(":landing"))

    implementation(libs.koin)
    implementation(libs.voyager.navigator)
    implementation(libs.timber)
}