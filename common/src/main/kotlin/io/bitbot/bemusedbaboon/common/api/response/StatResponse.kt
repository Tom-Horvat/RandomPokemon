package io.bitbot.bemusedbaboon.common.api.response

import com.squareup.moshi.Json

data class StatResponse (
    @param:Json(name = "base_stat")
    val baseStat: Int,
    val stat: NamedUrl
)