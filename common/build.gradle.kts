import com.google.protobuf.gradle.id

plugins {
    id("randompokemon.android.library")
    id("randompokemon.android.compose")
    id("randompokemon.kotlin.android")
    id("randompokemon.android.room")
    alias(libs.plugins.protobuf)
}

val apiUrl = "https://pokeapi.co/api/v2/"

android {
    namespace = "io.bitbot.bemusedbaboon.commons"

    buildTypes {
        debug {
            buildConfigField("String", "API_URL", "\"$apiUrl\"")
        }
        release {
            buildConfigField("String", "API_URL", "\"$apiUrl\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(libs.androidx.material.icons)
    implementation(libs.coroutines)

    implementation(libs.timber)

    implementation(libs.koin)
    implementation(libs.koin.compose)

    implementation(libs.datastore)
    implementation(libs.protobuf.javalite)
    implementation(libs.protobuf.kotlinlite)

    implementation(libs.moshi)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.retrofit.logging)

    debugImplementation(libs.androidx.ui.tooling)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.20.1"
    }
    plugins {
        id("javalite") {
            artifact = "com.google.protobuf:protoc-gen-javalite:3.0.0"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                id("java") {
                    option("lite")
                }
                id("kotlin") {
                    option("lite")
                }
            }
        }
    }
}
