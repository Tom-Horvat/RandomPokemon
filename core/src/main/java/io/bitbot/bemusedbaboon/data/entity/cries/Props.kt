package io.bitbot.bemusedbaboon.data.entity.cries

import io.bitbot.bemusedbaboon.data.entity.DbEntry

sealed interface Props : DbEntry {
    val cryId: Long
    val latest: String
    val legacy: String
    val pokemonId: Long
}