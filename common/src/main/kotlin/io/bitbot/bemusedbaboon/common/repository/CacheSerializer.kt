package io.bitbot.bemusedbaboon.common.repository

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import io.bitbot.bemusedbaboon.common.Cache
import java.io.InputStream
import java.io.OutputStream

object CacheSerializer : Serializer<Cache> {
    override val defaultValue: Cache = Cache.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): Cache {
        try {
            return Cache.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("", exception)
        }
    }

    override suspend fun writeTo(t: Cache, output: OutputStream) = t.writeTo(output)
}