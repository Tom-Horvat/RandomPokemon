package io.bitbot.bemusedbaboon.common.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonIndexResponse(
    val count: Int,
    val results: List<PokemonNamedUrl>
)
