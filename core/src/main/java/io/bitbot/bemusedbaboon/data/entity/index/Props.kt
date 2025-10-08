package io.bitbot.bemusedbaboon.data.entity.index

import io.bitbot.bemusedbaboon.data.entity.DbEntry

sealed interface Props : DbEntry {
    val pokemonId: Long
    val name: String
}