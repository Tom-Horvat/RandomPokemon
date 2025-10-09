package io.bitbot.bemusedbaboon.framework.di

import io.bitbot.bemusedbaboon.common.manager.PokemonCountLocalRepoImpl
import io.bitbot.bemusedbaboon.common.manager.PokemonCountRemoteRepoImpl
import io.bitbot.bemusedbaboon.domain.usecase.GetPokemonCount
import org.koin.dsl.module

/**
 * A module of all app resources that are injected
 */
val app = module {
    single {
        GetPokemonCount(
            local = get<PokemonCountLocalRepoImpl>(),
            remote = get<PokemonCountRemoteRepoImpl>()
        )
    }
}