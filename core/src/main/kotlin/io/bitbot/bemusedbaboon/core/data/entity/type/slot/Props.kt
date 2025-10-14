package io.bitbot.bemusedbaboon.core.data.entity.type.slot

import io.bitbot.bemusedbaboon.core.data.entity.Timestamp

sealed interface Props : Timestamp {
       val typeSlotId: Long
       val order: Int
       val typeId: Long
       val pokemonId: Long
}