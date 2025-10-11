package io.bitbot.bemusedbaboon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.Navigator
import io.bitbot.bemusedbaboon.core.data.entity.index.Index
import io.bitbot.bemusedbaboon.core.domain.usecase.index.GetPokemonCount
import io.bitbot.bemusedbaboon.core.domain.usecase.index.GetPokemonIndex
import io.bitbot.bemusedbaboon.navigation.landing.LandingScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : ComponentActivity() {
    private val getPokemonCount: GetPokemonCount by inject()
    private val getPokemonIndex: GetPokemonIndex by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashscreen = installSplashScreen()
        var showSplashScreen = true

        super.onCreate(savedInstanceState)

        splashscreen.setKeepOnScreenCondition { showSplashScreen }

        lifecycleScope.launch(Dispatchers.Default) {
            getPokemonCount.state.collect { useCase ->
                useCase?.parse<Any, Int>(
                    onError = { e ->
                        Timber.e(e)
                        showSplashScreen = false
                    }
                ) { data ->
                    data?.let {
                        getPokemonIndex(data)
                    } ?: run { showSplashScreen = false }
                }
            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            getPokemonIndex.state.collect { useCase ->
                useCase?.parse<Int, List<Index>>(
                    onError = { e -> Timber.e(e) }
                ) {
                    Timber.i("Index from use case has %d items", it?.size)
                    showSplashScreen = false
                }
            }
        }
        lifecycleScope.launch {
            getPokemonCount()
        }

        enableEdgeToEdge()
        setContent {
            val startScreen = rememberScreen(provider = LandingScreen.Home)

            Navigator(screen = startScreen)
        }
    }
}