plugins {
    id("randompokemon.android.library")
    id("randompokemon.android.compose")
}

android {
    namespace = "io.bitbot.bemusedbaboon.landing"

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

dependencies {

    implementation(project(":core"))
    implementation(project(":common"))
    implementation(project(":navigation"))

    implementation(libs.timber)

    implementation(libs.koin)
    implementation(libs.koin.compose)

    implementation(libs.voyager.navigator)

    implementation(libs.coil.compose)
    implementation(libs.coil.network)
}
