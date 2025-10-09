package io.bitbot.bemusedbaboon.data.repository.pokemoncount

import io.bitbot.scoreboard.core.data.repository.Result
import kotlinx.coroutines.flow.Flow

interface PokemonCountRepo {
    suspend fun getPokemonCount(): Flow<Result>
}