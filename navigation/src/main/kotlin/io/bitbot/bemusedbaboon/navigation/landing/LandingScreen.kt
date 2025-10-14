package io.bitbot.bemusedbaboon.navigation.landing

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class LandingScreen : ScreenProvider{
    data object Home: LandingScreen()
}