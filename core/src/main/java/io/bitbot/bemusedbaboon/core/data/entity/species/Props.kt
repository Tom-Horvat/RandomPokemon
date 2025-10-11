package io.bitbot.bemusedbaboon.core.data.entity.species

import io.bitbot.bemusedbaboon.core.data.entity.Timestamp

interface Props: Timestamp {
    val speciesId: Int
    val name: String
    val pokemonId: Long
}