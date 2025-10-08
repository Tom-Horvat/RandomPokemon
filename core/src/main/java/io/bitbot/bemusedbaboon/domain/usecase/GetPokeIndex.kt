package io.bitbot.bemusedbaboon.domain.usecase

import io.bitbot.bemusedbaboon.data.repository.PokemonDataSource
import io.bitbot.bemusedbaboon.domain.PokeIndex
import kotlinx.coroutines.flow.Flow

/**
 * A use case for getting a pokemon index.
 */
class GetPokeIndex(private val dataSource: PokemonDataSource) {
    suspend operator fun invoke(fromCache: Boolean = true): Flow<PokeIndex?> =
        dataSource.getPokeIndex(fromCache = fromCache)
}