package io.bitbot.bemusedbaboon.landing.framework

import cafe.adriel.voyager.core.registry.screenModule
import io.bitbot.bemusedbaboon.landing.home.HomeScreen
import io.bitbot.bemusedbaboon.navigation.landing.LandingScreen


val landingNav = screenModule {
    register<LandingScreen.Home> {
        HomeScreen()
    }
}