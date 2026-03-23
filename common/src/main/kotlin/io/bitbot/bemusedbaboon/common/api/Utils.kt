package io.bitbot.bemusedbaboon.common.api

import io.bitbot.bemusedbaboon.commons.BuildConfig

fun String.stripUrlPrefix(endpoint: String): Long = replace("${BuildConfig.API_URL}$endpoint", "")
    .replace(PokemonApi.DASH, "")
    .toLong()