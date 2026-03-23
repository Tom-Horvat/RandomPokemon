package io.bitbot.bemusedbaboon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.Navigator
import io.bitbot.bemusedbaboon.common.ui.theme.RandomPokemonTheme
import io.bitbot.bemusedbaboon.core.domain.usecase.index.GetPokemonCount
import io.bitbot.bemusedbaboon.landing.framework.navigation.KoinLandingModule
import io.bitbot.bemusedbaboon.navigation.landing.LandingScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : ComponentActivity() {
    private val getPokemonCount: GetPokemonCount by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashscreen = installSplashScreen()
        var showSplashScreen = true

        super.onCreate(savedInstanceState)

        splashscreen.setKeepOnScreenCondition { showSplashScreen }
        lifecycleScope.launch(Dispatchers.IO) {
            getPokemonCount.state.collect { state ->
                state?.parse<Int>(onError = { Timber.e(it) }) {
                    showSplashScreen = false
                }
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            getPokemonCount()
        }

        enableEdgeToEdge()
        setContent {
            val startScreen = rememberScreen(provider = LandingScreen.Home)

            RandomPokemonTheme {
                KoinLandingModule { Navigator(screen = startScreen) }
            }
        }
    }
}