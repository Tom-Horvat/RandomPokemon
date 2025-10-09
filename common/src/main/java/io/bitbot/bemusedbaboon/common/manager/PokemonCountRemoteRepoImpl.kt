package io.bitbot.bemusedbaboon.common.manager

import io.bitbot.bemusedbaboon.common.api.PokemonApi
import io.bitbot.bemusedbaboon.data.repository.ResultFlowConverter
import io.bitbot.bemusedbaboon.data.repository.pokemoncount.PokemonCountRepo

class PokemonCountRemoteRepoImpl(
    private val api: PokemonApi
) : ResultFlowConverter(), PokemonCountRepo {
    override suspend fun getPokemonCount() = toResultFlow {
        api.getPokemonCount().count
    }
}