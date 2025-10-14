package io.bitbot.bemusedbaboon.core.data.entity.index

sealed interface Props {
    val pokemonId: Long
    val name: String
}