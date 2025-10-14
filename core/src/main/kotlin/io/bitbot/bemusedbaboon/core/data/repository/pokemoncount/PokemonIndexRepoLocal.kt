package io.bitbot.bemusedbaboon.core.data.repository.pokemoncount

import io.bitbot.bemusedbaboon.core.data.entity.index.Index
import io.bitbot.bemusedbaboon.core.domain.ResultState
import kotlinx.coroutines.flow.Flow

interface PokemonIndexRepoLocal : PokemonIndexRepo {
    suspend fun setPokemonCount(count: Int): Flow<ResultState>
    suspend fun setPokemonIndex(items: List<Index>): Flow<ResultState>
    suspend fun getPokemonIndexCount(): Flow<ResultState>
}