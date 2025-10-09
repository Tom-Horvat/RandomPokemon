package io.bitbot.bemusedbaboon.data.repository.pokemoncount

import io.bitbot.scoreboard.core.data.repository.Result
import kotlinx.coroutines.flow.Flow

interface PokemonCountRepoLocal : PokemonCountRepo {
    suspend fun setPokemonCount(count: Int): Flow<Result>
}