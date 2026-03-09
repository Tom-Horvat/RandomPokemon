package io.bitbot.bemusedbaboon.landing.framework

import io.bitbot.bemusedbaboon.core.domain.usecase.index.GetPokemon
import io.bitbot.bemusedbaboon.core.domain.usecase.index.GetRandomIndex
import io.bitbot.bemusedbaboon.landing.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val landing = module {
    single { GetRandomIndex(local = get()) }
    single { GetPokemon(local = get(), remote = get()) }

    viewModel {
        HomeViewModel(
            getPokemonIndex = get(),
            getRandomIndex = get(),
            getPokemon = get(),
        )
    }
}