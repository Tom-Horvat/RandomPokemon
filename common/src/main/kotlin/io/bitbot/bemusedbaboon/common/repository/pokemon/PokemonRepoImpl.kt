package io.bitbot.bemusedbaboon.common.repository.pokemon

import io.bitbot.bemusedbaboon.common.api.PokemonApi
import io.bitbot.bemusedbaboon.common.api.response.toDto
import io.bitbot.bemusedbaboon.core.data.repository.pokemon.PokemonRepo
import io.bitbot.bemusedbaboon.core.domain.ResultStateFlowConverter

class PokemonRepoImpl(
    private val api: PokemonApi
) : ResultStateFlowConverter(), PokemonRepo {
    override suspend fun getPokemonById(id: Long) = toResultStateFlow {
        api.getPokemonById(id = id.toInt()).toDto()
    }
}