package io.bitbot.bemusedbaboon.framework.di

import io.bitbot.bemusedbaboon.common.manager.PokemonIndexLocalRepoImpl
import io.bitbot.bemusedbaboon.common.manager.PokemonIndexRemoteRepoImpl
import io.bitbot.bemusedbaboon.core.domain.usecase.index.GetPokemonCount
import io.bitbot.bemusedbaboon.core.domain.usecase.index.GetPokemonIndex
import org.koin.dsl.module

/**
 * A module of all app resources that are injected
 */
val app = module {
    single {
        GetPokemonCount(
            local = get<PokemonIndexLocalRepoImpl>(),
            remote = get<PokemonIndexRemoteRepoImpl>()
        )
    }
    single {
        GetPokemonIndex(
            local = get<PokemonIndexLocalRepoImpl>(),
            remote = get<PokemonIndexRemoteRepoImpl>()
        )
    }
}