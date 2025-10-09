package io.bitbot.bemusedbaboon.common.provider

import io.bitbot.bemusedbaboon.common.api.PokemonApi
import retrofit2.Retrofit

/**
 * Provides the [PokemonApi] instance
 *
 * @param retrofit an instance of [Retrofit]
 */
fun providePokemonApi(retrofit: Retrofit): PokemonApi = retrofit.create(PokemonApi::class.java)