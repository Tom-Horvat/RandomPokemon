import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class RandomPokemonAndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")
            pluginManager.apply("org.jetbrains.kotlin.android")

            extensions.configure<BaseAppModuleExtension> {
                configureKotlinAndroid(this)
                namespace = BASE_NAMESPACE
                defaultConfig {
                    applicationId = BASE_NAMESPACE
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
        }
    }
}