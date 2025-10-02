package io.bitbot.bemusedbaboon.data.entity.species

import io.bitbot.bemusedbaboon.data.entity.DbEntry

interface Props: DbEntry {
    val speciesId: Int
    val name: String
    val pokemonId: Long
}