package io.bitbot.bemusedbaboon.data.repository

import io.bitbot.bemusedbaboon.domain.PokeIndex
import io.bitbot.bemusedbaboon.domain.Pokemon
import kotlinx.coroutines.flow.Flow

/**
 * An interface for the data source.
 */
interface PokemonDataSource {
    suspend fun getPokemon(fromCache: Boolean = true, pokemonId: Int): Flow<Pokemon?>
    suspend fun getPokeIndex(fromCache: Boolean = true): Flow<PokeIndex?>
    suspend fun cachePokemon(pokemon: Pokemon): Flow<Any?>
    fun cachePokeIndex(pokeIndex: PokeIndex): Flow<Any?>
}