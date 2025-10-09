package io.bitbot.bemusedbaboon.common.provider

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
 * Provides a [Moshi] instance
 */
fun provideMoshi(): Moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()