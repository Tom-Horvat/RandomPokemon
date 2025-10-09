package io.bitbot.bemusedbaboon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.Navigator
import io.bitbot.bemusedbaboon.domain.usecase.GetPokemonCount
import io.bitbot.bemusedbaboon.domain.usecase.UseCaseState
import io.bitbot.bemusedbaboon.navigation.landing.LandingScreen
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val getPokemonCount: GetPokemonCount by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashscreen = installSplashScreen()
        var showSplashScreen = true

        super.onCreate(savedInstanceState)

        splashscreen.setKeepOnScreenCondition { showSplashScreen }

        lifecycleScope.launch {
            getPokemonCount.state.collect {
                when (it) {
                    is UseCaseState.Done<*> -> showSplashScreen = false
                    is UseCaseState.Error<*> -> showSplashScreen = false
                    is UseCaseState.Running -> {}
                    null -> {}
                }
            }
        }
        lifecycleScope.launch {
            getPokemonCount.invoke()
        }

        enableEdgeToEdge()
        setContent {
            val startScreen = rememberScreen(provider = LandingScreen.Home)

            Navigator(screen = startScreen)
        }
    }
}