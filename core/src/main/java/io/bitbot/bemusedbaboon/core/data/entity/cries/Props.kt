package io.bitbot.bemusedbaboon.core.data.entity.cries

import io.bitbot.bemusedbaboon.core.data.entity.Timestamp

sealed interface Props : Timestamp {
    val cryId: Long
    val latest: String
    val legacy: String
    val pokemonId: Long
}