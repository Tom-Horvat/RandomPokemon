package io.bitbot.bemusedbaboon.common.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.bitbot.bemusedbaboon.commons.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun provideOkHttp(): OkHttpClient = OkHttpClient().newBuilder().build()

fun provideMoshi(): Moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    moshi: Moshi,
): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.API_URL)
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

fun providePokemonApi(retrofit: Retrofit): PokemonApi = retrofit.create(PokemonApi::class.java)
