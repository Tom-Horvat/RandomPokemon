package io.bitbot.bemusedbaboon.framework.di

import io.bitbot.bemusedbaboon.core.domain.usecase.index.GetPokemonCount
import io.bitbot.bemusedbaboon.core.domain.usecase.index.GetPokemonIndex
import org.koin.dsl.module

val app = module {
    single { GetPokemonCount(repo = get(), logger = get()) }
    single { GetPokemonIndex(repo = get(), logger = get()) }
}