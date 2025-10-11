package io.bitbot.bemusedbaboon.common.manager

import android.content.Context
import io.bitbot.bemusedbaboon.common.provider.cacheDataStore
import io.bitbot.bemusedbaboon.core.data.dao.IndexDao
import io.bitbot.bemusedbaboon.core.data.entity.index.Index
import io.bitbot.bemusedbaboon.core.data.repository.ResultFlowConverter
import io.bitbot.bemusedbaboon.core.data.repository.pokemoncount.PokemonIndexRepoLocal
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PokemonIndexLocalRepoImpl(
    private val context: Context,
    private val indexDao: IndexDao
) : ResultFlowConverter(),
    PokemonIndexRepoLocal {

    override suspend fun setPokemonCount(count: Int) = toResultFlow {
        context.cacheDataStore.updateData { current ->
            current.toBuilder()
                .setCount(count)
                .build()
        }
        count
    }

    override suspend fun setPokemonIndex(items: List<Index>) = toResultFlow {
        indexDao.insertAll(items)
    }

    override suspend fun getPokemonIndexCount() = toResultFlow {
        indexDao.getIndexCount()
    }

    override suspend fun getPokemonCount() = toResultFlow {
        context.cacheDataStore.data.map { cache -> cache.count }.first()
    }

    override suspend fun getPokemonIndex(count: Int) = toResultFlow {
        indexDao.getAll()
    }
}