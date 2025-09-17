package io.bitbot.bemusedbaboon.usecase

import io.bitbot.bemusedbaboon.data.PokemonDataSource
import io.bitbot.bemusedbaboon.domain.Pokemon
import kotlinx.coroutines.flow.Flow

/**
 * A use case for caching a pokemon.
 */
class CachePokemon(private val dataSource: PokemonDataSource) {
    suspend operator fun invoke(pokemon: Pokemon): Flow<Any?> =
        dataSource.cachePokemon(pokemon = pokemon)
}