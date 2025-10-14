import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "io.bitbot.bemusedbaboon"
    compileSdk = 36

    defaultConfig {
        applicationId = "io.bitbot.bemusedbaboon"
        minSdk = 31
        targetSdk = 36
        versionCode = 1
        versionName = "2.0.0-alpha"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isDebuggable = true
            applicationIdSuffix = ".debug"
        }
        release {
            isDebuggable = false
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlin{
        compilerOptions{
            jvmTarget = JvmTarget.JVM_21
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":common"))
    implementation(project(":navigation"))
    implementation(project(":landing"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.splashscreem)

    implementation(libs.koin)
    implementation(libs.voyager.navigator)
    implementation(libs.timber)
}