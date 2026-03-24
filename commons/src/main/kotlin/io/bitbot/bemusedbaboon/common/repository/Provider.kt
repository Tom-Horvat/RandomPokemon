package io.bitbot.bemusedbaboon.common.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.room.Room
import io.bitbot.bemusedbaboon.common.Cache
import io.bitbot.bemusedbaboon.core.data.PokemonDatabase

val Context.cacheDataStore: DataStore<Cache> by dataStore(
    fileName = "cache.pb",
    serializer = CacheSerializer
)

fun provideDatabase(context: Context) =
    Room.databaseBuilder(context, PokemonDatabase::class.java, "db").build()