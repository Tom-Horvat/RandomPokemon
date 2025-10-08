package io.bitbot.bemusedbaboon.domain.usecase

import io.bitbot.bemusedbaboon.data.repository.PokemonDataSource
import io.bitbot.bemusedbaboon.domain.PokeIndex
import kotlinx.coroutines.flow.Flow

/**
 * The use case for caching the pokemon index.
 */
class CachePokeIndex(private val dataSource: PokemonDataSource) {
    operator fun invoke(pokeIndex: PokeIndex): Flow<Any?> =
        dataSource.cachePokeIndex(pokeIndex)
}