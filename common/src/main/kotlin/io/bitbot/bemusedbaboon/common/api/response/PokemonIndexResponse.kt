package io.bitbot.bemusedbaboon.common.api.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonIndexResponse(
    val count: Int,
    val results: List<PokemonNamedUrl>
)

fun List<PokemonNamedUrl>.toEntities() = map { it.toEntity() }
