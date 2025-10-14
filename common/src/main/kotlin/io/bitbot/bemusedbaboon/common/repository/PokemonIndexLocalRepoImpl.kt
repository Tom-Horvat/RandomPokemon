package io.bitbot.bemusedbaboon.common.repository

import android.content.Context
import io.bitbot.bemusedbaboon.core.data.dao.IndexDao
import io.bitbot.bemusedbaboon.core.data.entity.index.Index
import io.bitbot.bemusedbaboon.core.domain.ResultStateFlowConverter
import io.bitbot.bemusedbaboon.core.data.repository.pokemoncount.PokemonIndexRepoLocal
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PokemonIndexLocalRepoImpl(
    private val context: Context,
    private val indexDao: IndexDao
) : ResultStateFlowConverter(),
    PokemonIndexRepoLocal {

    override suspend fun setPokemonCount(count: Int) = toResultStateFlow {
        context.cacheDataStore.updateData { current ->
            current.toBuilder()
                .setCount(count)
                .build()
        }
        count
    }

    override suspend fun setPokemonIndex(items: List<Index>) = toResultStateFlow {
        indexDao.insertAll(items)
    }

    override suspend fun getPokemonIndexCount() = toResultStateFlow {
        indexDao.getIndexCount()
    }

    override suspend fun getPokemonCount() = toResultStateFlow {
        context.cacheDataStore.data.map { cache -> cache.count }.first()
    }

    override suspend fun getPokemonIndex(count: Int) = toResultStateFlow {
        indexDao.getAll()
    }
}