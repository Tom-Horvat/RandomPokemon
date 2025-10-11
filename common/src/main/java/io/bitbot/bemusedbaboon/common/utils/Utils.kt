package io.bitbot.bemusedbaboon.common.utils

import io.bitbot.bemusedbaboon.commons.BuildConfig

fun String.stripUrlPrefix(): Long = replace("${BuildConfig.API_URL}pokemon", "")
    .replace("/", "")
    .toLong()