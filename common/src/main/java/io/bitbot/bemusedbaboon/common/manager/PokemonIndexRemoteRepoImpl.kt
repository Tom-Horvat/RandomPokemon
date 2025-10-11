package io.bitbot.bemusedbaboon.common.manager

import io.bitbot.bemusedbaboon.common.api.PokemonApi
import io.bitbot.bemusedbaboon.common.api.toEntities
import io.bitbot.bemusedbaboon.core.data.repository.ResultFlowConverter
import io.bitbot.bemusedbaboon.core.data.repository.pokemoncount.PokemonIndexRepo

class PokemonIndexRemoteRepoImpl(
    private val api: PokemonApi
) : ResultFlowConverter(), PokemonIndexRepo {
    override suspend fun getPokemonCount() = toResultFlow {
        api.getPokemonCount().count
    }

    override suspend fun getPokemonIndex(count: Int) = toResultFlow {
        api.getPokemonCount(count = count).results.toEntities()
    }
}