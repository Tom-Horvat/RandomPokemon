package io.bitbot.bemusedbaboon.common.repository.index

import io.bitbot.bemusedbaboon.common.api.PokemonApi
import io.bitbot.bemusedbaboon.common.api.response.toEntities
import io.bitbot.bemusedbaboon.core.data.repository.index.IndexRepo
import io.bitbot.bemusedbaboon.core.domain.ResultStateFlowConverter

class IndexRepoImpl(
    private val api: PokemonApi
) : ResultStateFlowConverter(), IndexRepo {
    override suspend fun getPokemonCount() = toResultStateFlow {
        api.getPokemonCount().count
    }

    override suspend fun getPokemonIndex(count: Int) = toResultStateFlow {
        api.getPokemonCount(count = count).results.toEntities(PokemonApi.POKEMON)
    }
}