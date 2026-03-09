package io.bitbot.bemusedbaboon.common.api.response

import io.bitbot.bemusedbaboon.common.api.stripUrlPrefix
import io.bitbot.bemusedbaboon.core.data.entity.index.Index

data class NamedUrl(
    val name: String,
    val url: String
)

fun NamedUrl.toEntity(endpoint: String) = Index(
    pokemonId = url.stripUrlPrefix(endpoint = endpoint),
    name = name,
)
