package io.bitbot.bemusedbaboon.common.provider

import com.squareup.moshi.Moshi
import io.bitbot.bemusedbaboon.commons.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Provides a [Retrofit] instance.
 *
 * @param okHttpClient
 * @param moshi
 */
fun provideRetrofit(
    okHttpClient: OkHttpClient,
    moshi: Moshi,
): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.API_URL)
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()