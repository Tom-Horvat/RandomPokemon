package io.bitbot.bemusedbaboon.landing.framework.di

import io.bitbot.bemusedbaboon.core.domain.usecase.index.GetRandomIndex
import io.bitbot.bemusedbaboon.core.domain.usecase.pokemon.GetPokemon
import io.bitbot.bemusedbaboon.landing.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val landing = module {
    single {
        GetRandomIndex(
            local = get(),
            initializationProvider = get(),
            logger = get()
        )
    }
    single {
        GetPokemon(
            repo = get(),
            logger = get()
        )
    }

    viewModel {
        HomeViewModel(
            getPokemonCount = get(),
            getPokemonIndex = get(),
            getRandomIndex = get(),
            getPokemon = get(),
        )
    }
}