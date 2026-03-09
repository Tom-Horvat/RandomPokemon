package io.bitbot.bemusedbaboon.core.data.repository.index

import io.bitbot.bemusedbaboon.core.data.entity.index.Index
import io.bitbot.bemusedbaboon.core.domain.ResultState
import kotlinx.coroutines.flow.Flow

interface IndexRepo {
    suspend fun getPokemonCount(): Flow<ResultState>
    suspend fun getPokemonIndex(count: Int = 0): Flow<ResultState>

    interface Local : IndexRepo {
        suspend fun setPokemonCount(count: Int): Flow<ResultState>
        suspend fun setPokemonIndex(items: List<Index>): Flow<ResultState>
        suspend fun getPokemonIndexCount(): Flow<ResultState>
        suspend fun getRandomIndex(): Flow<ResultState>
    }
}