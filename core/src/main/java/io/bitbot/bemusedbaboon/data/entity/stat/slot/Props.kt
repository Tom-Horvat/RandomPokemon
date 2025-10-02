package io.bitbot.bemusedbaboon.data.entity.stat.slot

import io.bitbot.bemusedbaboon.data.entity.DbEntry

sealed interface Props : DbEntry {
      val statSlotId: Long
      val baseStat: Int
      val effort: Int
      val statId: Long
      val pokemonId: Long
}