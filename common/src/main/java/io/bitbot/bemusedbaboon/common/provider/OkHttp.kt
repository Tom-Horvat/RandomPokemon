package io.bitbot.bemusedbaboon.common.provider

import okhttp3.OkHttpClient

/**
 * Provides the [OkHttpClient] instance
 */
fun provideOkHttp(): OkHttpClient = OkHttpClient().newBuilder().build()
