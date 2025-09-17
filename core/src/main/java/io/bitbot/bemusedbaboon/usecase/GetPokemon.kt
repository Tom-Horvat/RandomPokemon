package io.bitbot.bemusedbaboon.usecase

import io.bitbot.bemusedbaboon.data.PokemonDataSource

/**
 * A use case for getting a pokemon.
 */
class GetPokemon(private val dataSource: PokemonDataSource) {
    suspend operator fun invoke(fromCache: Boolean = true, pokemonId: Int) =
        dataSource.getPokemon(fromCache = fromCache, pokemonId = pokemonId)
}