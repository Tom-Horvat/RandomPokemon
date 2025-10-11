package io.bitbot.bemusedbaboon.core.data.repository.pokemoncount

import io.bitbot.bemusedbaboon.core.data.entity.index.Index
import io.bitbot.scoreboard.core.data.repository.Result
import kotlinx.coroutines.flow.Flow

interface PokemonIndexRepoLocal : PokemonIndexRepo {
    suspend fun setPokemonCount(count: Int): Flow<Result>
    suspend fun setPokemonIndex(items: List<Index>): Flow<Result>
    suspend fun getPokemonIndexCount(): Flow<Result>
}