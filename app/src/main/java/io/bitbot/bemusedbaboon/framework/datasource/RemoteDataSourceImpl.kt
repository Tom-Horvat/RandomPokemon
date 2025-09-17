package io.bitbot.bemusedbaboon.framework.datasource

import io.bitbot.bemusedbaboon.data.PokemonDataSource
import io.bitbot.bemusedbaboon.domain.PokeIndex
import io.bitbot.bemusedbaboon.domain.Pokemon
import io.bitbot.bemusedbaboon.data.database.PokemonDto
import io.bitbot.bemusedbaboon.framework.PokemonApi
import kotlinx.coroutines.flow.*

/**
 * An implementation od [PokemonDataSource] that uses the [PokemonApi] as a data source.
 *
 * @property api the API interface
 */
class RemoteDataSourceImpl(
    private val api: PokemonApi,
) : PokemonDataSource {
    /**
     * Gets a [PokemonDto] from the API, creates a [Flow] and emits a [Pokemon] converted from the
     * DTO
     */
    override suspend fun getPokemon(fromCache: Boolean, pokemonId: Int): Flow<Pokemon> = flow {
        emit(api.getPokemon(id = pokemonId).toObject())
    }

    /**
     * Gets a [PokeIndex] object from the API, creates a [Flow] and emits the value
     */
    override suspend fun getPokeIndex(fromCache: Boolean): Flow<PokeIndex> = flow {
        emit(api.getPokeIndex().toObject())
    }

    /**
     * Unused
     */
    override suspend fun cachePokemon(pokemon: Pokemon): Flow<Any?> = flow {
        // do nothing
    }

    /**
     * Unused
     */
    override fun cachePokeIndex(pokeIndex: PokeIndex): Flow<Boolean> = flow {
        // do nothing
    }
}