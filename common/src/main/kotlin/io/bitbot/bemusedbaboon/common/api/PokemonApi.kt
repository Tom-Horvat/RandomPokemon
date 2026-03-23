package io.bitbot.bemusedbaboon.common.api

import io.bitbot.bemusedbaboon.common.api.response.PokemonIndexResponse
import io.bitbot.bemusedbaboon.common.api.response.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    companion object {
        const val DASH = "/"
        const val POKEMON = "pokemon"
        const val ABILITY = "ability"
        const val LIMIT = "limit"
        const val OFFSET = "offset"
    }

    @GET(POKEMON + DASH)
    suspend fun getPokemonCount(
        @Query(LIMIT) count: Int = 1,
        @Query(OFFSET) offset: Int = 0
    ): PokemonIndexResponse

    @GET("$POKEMON$DASH{id}$DASH")
    suspend fun getPokemonById(@Path("id") id: Int): PokemonResponse
}