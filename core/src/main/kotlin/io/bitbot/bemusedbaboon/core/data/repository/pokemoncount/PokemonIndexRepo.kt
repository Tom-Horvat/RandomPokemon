package io.bitbot.bemusedbaboon.core.data.repository.pokemoncount

import io.bitbot.bemusedbaboon.core.domain.ResultState
import kotlinx.coroutines.flow.Flow

interface PokemonIndexRepo {
    suspend fun getPokemonCount(): Flow<ResultState>
    suspend fun getPokemonIndex(count: Int = 0): Flow<ResultState>
}