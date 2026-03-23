package io.bitbot.bemusedbaboon.landing.framework.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import cafe.adriel.voyager.core.registry.screenModule
import io.bitbot.bemusedbaboon.landing.framework.di.landing
import io.bitbot.bemusedbaboon.landing.home.HomeScreen
import io.bitbot.bemusedbaboon.navigation.landing.LandingScreen
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

val landingNav = screenModule {
    register<LandingScreen.Home> {
        HomeScreen()
    }
}

@Composable
fun KoinLandingModule(content: @Composable () -> Unit) {
    loadKoinModules(landing)
    content()

    DisposableEffect(Unit) { onDispose { unloadKoinModules(landing) } }
}