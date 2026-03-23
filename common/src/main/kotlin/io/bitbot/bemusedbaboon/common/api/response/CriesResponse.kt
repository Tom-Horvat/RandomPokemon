package io.bitbot.bemusedbaboon.common.api.response

import io.bitbot.bemusedbaboon.core.data.entity.cries.Cries

data class CriesResponse(
    val latest: String,
    val legacy: String?,
)

fun CriesResponse.toEntity(id: Long) = Cries(
    pokemonId = id,
    latest = latest,
    legacy = legacy
)
