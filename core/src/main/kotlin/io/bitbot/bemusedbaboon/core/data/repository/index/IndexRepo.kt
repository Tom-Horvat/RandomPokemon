package io.bitbot.bemusedbaboon.core.data.repository.index

import io.bitbot.bemusedbaboon.core.data.entity.index.Index
import io.bitbot.bemusedbaboon.core.domain.ResultState
import kotlinx.coroutines.flow.Flow

interface IndexRepo {
    fun getPokemonIndex(count: Int = 0): Flow<ResultState>
    fun getPokemonCount(): Flow<ResultState>

    interface Remote : IndexRepo

    interface Local : IndexRepo {
        fun getPokemonIndexCount(): Flow<ResultState>
        fun getRandomIndex(): Flow<ResultState>
        suspend fun setPokemonCount(count: Int)
        suspend fun setPokemonIndex(items: List<Index>)
    }
}