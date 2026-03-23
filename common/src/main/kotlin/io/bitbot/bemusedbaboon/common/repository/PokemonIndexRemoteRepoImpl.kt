package io.bitbot.bemusedbaboon.common.repository

import io.bitbot.bemusedbaboon.common.api.PokemonApi
import io.bitbot.bemusedbaboon.common.api.response.toEntities
import io.bitbot.bemusedbaboon.core.domain.ResultStateFlowConverter
import io.bitbot.bemusedbaboon.core.data.repository.pokemoncount.PokemonIndexRepo

class PokemonIndexRemoteRepoImpl(
    private val api: PokemonApi
) : ResultStateFlowConverter(), PokemonIndexRepo {
    override suspend fun getPokemonCount() = toResultStateFlow {
        api.getPokemonCount().count
    }

    override suspend fun getPokemonIndex(count: Int) = toResultStateFlow {
        api.getPokemonCount(count = count).results.toEntities()
    }
}