package io.bitbot.bemusedbaboon.data.entity.type.slot

import io.bitbot.bemusedbaboon.data.entity.DbEntry

sealed interface Props : DbEntry {
       val typeSlotId: Long
       val order: Int
       val typeId: Long
       val pokemonId: Long
}