import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class RandomPokemonAndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")
            pluginManager.apply("org.jetbrains.kotlin.android")

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            extensions.configure<BaseAppModuleExtension> {
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

                buildFeatures {
                    buildConfig = true
                }
            }

            dependencies {
                "implementation"(libs.findLibrary("androidx.activity.compose").get())
                "implementation"(libs.findLibrary("splashscreen").get())
            }
        }
    }
}