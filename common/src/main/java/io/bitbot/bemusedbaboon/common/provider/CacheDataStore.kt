package io.bitbot.bemusedbaboon.common.provider

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import io.bitbot.bemusedbaboon.common.Cache
import io.bitbot.bemusedbaboon.common.serializer.CacheSerializer

val Context.cacheDataStore: DataStore<Cache> by dataStore(
    fileName = "cache.pb",
    serializer = CacheSerializer
)