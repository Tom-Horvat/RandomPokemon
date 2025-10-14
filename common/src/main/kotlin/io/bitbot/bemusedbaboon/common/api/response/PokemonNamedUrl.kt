package io.bitbot.bemusedbaboon.common.api.response

import io.bitbot.bemusedbaboon.common.api.stripUrlPrefix
import io.bitbot.bemusedbaboon.core.data.entity.index.Index

data class PokemonNamedUrl(
    val name: String,
    val url: String
)

fun PokemonNamedUrl.toEntity() = Index(
    pokemonId = url.stripUrlPrefix(),
    name = name,
)
