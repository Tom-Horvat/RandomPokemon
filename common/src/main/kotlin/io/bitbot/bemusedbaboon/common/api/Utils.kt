package io.bitbot.bemusedbaboon.common.api

import io.bitbot.bemusedbaboon.commons.BuildConfig

fun String.stripUrlPrefix(): Long = replace("${BuildConfig.API_URL}${PokemonApi.POKEMON}", "")
    .replace(PokemonApi.DASH, "")
    .toLong()