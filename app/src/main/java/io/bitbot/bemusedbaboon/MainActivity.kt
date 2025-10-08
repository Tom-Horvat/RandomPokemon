package io.bitbot.bemusedbaboon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.Navigator
import io.bitbot.bemusedbaboon.navigation.landing.LandingScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashscreen = installSplashScreen()
        var showSplashScreen = true

        super.onCreate(savedInstanceState)

        splashscreen.setKeepOnScreenCondition { showSplashScreen }
        lifecycleScope.launch {
            delay(3000)
            showSplashScreen = false
        }

        enableEdgeToEdge()
        setContent {
            val startScreen = rememberScreen(provider = LandingScreen.Home)

            Navigator(screen = startScreen)
        }
    }
}