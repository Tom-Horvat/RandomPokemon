package io.bitbot.bemusedbaboon.framework.provider

import io.bitbot.bemusedbaboon.framework.PokemonApi
import retrofit2.Retrofit

/**
 * Provides the [PokemonApi] instance
 *
 * @param retrofit an instance of [Retrofit]
 */
fun providePokemonApi(retrofit: Retrofit): PokemonApi = retrofit.create(PokemonApi::class.java)