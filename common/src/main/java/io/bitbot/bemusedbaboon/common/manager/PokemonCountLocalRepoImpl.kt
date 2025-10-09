package io.bitbot.bemusedbaboon.common.manager

import android.content.Context
import io.bitbot.bemusedbaboon.common.provider.cacheDataStore
import io.bitbot.bemusedbaboon.data.repository.ResultFlowConverter
import io.bitbot.bemusedbaboon.data.repository.pokemoncount.PokemonCountRepoLocal
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PokemonCountLocalRepoImpl(private val context: Context) : ResultFlowConverter(),
    PokemonCountRepoLocal {

    override suspend fun setPokemonCount(count: Int) = toResultFlow {
        context.cacheDataStore.updateData { current ->
            current.toBuilder()
                .setCount(count)
                .build()
        }
        count
    }

    override suspend fun getPokemonCount() = toResultFlow {
        context.cacheDataStore.data.map { cache -> cache.count }.first()
    }
}