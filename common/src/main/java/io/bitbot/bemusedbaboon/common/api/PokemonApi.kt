package io.bitbot.bemusedbaboon.common.api

import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon/")
    suspend fun getPokemonCount(
        @Query("limit") count: Int = 1,
        @Query("offset") offset: Int = 0
    ): PokemonIndexResponse
}