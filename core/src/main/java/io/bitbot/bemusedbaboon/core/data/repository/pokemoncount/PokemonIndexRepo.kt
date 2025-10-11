package io.bitbot.bemusedbaboon.core.data.repository.pokemoncount

import io.bitbot.scoreboard.core.data.repository.Result
import kotlinx.coroutines.flow.Flow

interface PokemonIndexRepo {
    suspend fun getPokemonCount(): Flow<Result>
    suspend fun getPokemonIndex(count: Int = 0): Flow<Result>
}