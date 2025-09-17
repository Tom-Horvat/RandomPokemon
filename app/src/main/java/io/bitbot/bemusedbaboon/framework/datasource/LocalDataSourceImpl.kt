package io.bitbot.bemusedbaboon.framework.datasource

import io.bitbot.bemusedbaboon.data.PokemonDataSource
import io.bitbot.bemusedbaboon.domain.PokeIndex
import io.bitbot.bemusedbaboon.domain.Pokemon
import io.bitbot.bemusedbaboon.adapter.PokemonJsonAdapter
import io.bitbot.bemusedbaboon.data.database.PokemonDao
import io.bitbot.bemusedbaboon.data.database.PokemonDto
import io.bitbot.bemusedbaboon.framework.PrefsCache
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * An implementation od [PokemonDataSource] that uses a [Room] database and [SharedPreferences] as data sources.
 *
 * @param prefsCache an interface for getting and setting the pokemon index data to the shared preferences
 * @param pokemonDao a data access object for working with the Room database
 * @param adapter a JSON adapter for use with [PokemonDao] to save data in JSON form to the database
 */
class LocalDataSourceImpl(
    private val prefsCache: PrefsCache,
    private val pokemonDao: PokemonDao,
    private val adapter: PokemonJsonAdapter,
) : PokemonDataSource {

    /**
     * Gets a [PokemonDto] from the Room database, creates a [Flow] and emits a [Pokemon] parsed
     * from the DTO
     */
    override suspend fun getPokemon(fromCache: Boolean, pokemonId: Int): Flow<Pokemon?> = flow {
        emit(pokemonDao.getPokemon(pokemonId)?.let { adapter.parseJson(it.data) })
    }

    /**
     * Gets a [PokeIndex] object or null from the [SharedPreferences], creates a [Flow] and emits
     * the value
     */
    override suspend fun getPokeIndex(fromCache: Boolean): Flow<PokeIndex?> = flow {
        emit(prefsCache.getPokeIndex())
    }

    /**
     * Saves a [PokemonDto] to the database and emits a [null] value
     */
    override suspend fun cachePokemon(pokemon: Pokemon): Flow<Any?> = flow {
        pokemonDao.insertPokemon(
            pokemon = PokemonDto(
                id = pokemon.id,
                data = adapter.toString(pokemon = pokemon)
            )
        )
        emit(null)
    }

    /**
     * Saves a [PokeIndex] to [SharedPreferences]
     */
    override fun cachePokeIndex(pokeIndex: PokeIndex): Flow<Any?> = flow {
        prefsCache.savePokeIndex(pokeIndex = pokeIndex)
        emit(null)
    }
}