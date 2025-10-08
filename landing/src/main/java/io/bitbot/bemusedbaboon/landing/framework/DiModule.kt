package io.bitbot.bemusedbaboon.landing.framework

import io.bitbot.bemusedbaboon.landing.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val landingModule = module {
    viewModel { HomeViewModel() }
}