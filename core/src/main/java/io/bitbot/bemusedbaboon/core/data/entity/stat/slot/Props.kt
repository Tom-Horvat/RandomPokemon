package io.bitbot.bemusedbaboon.core.data.entity.stat.slot

import io.bitbot.bemusedbaboon.core.data.entity.Timestamp

sealed interface Props : Timestamp {
      val statSlotId: Long
      val baseStat: Int
      val effort: Int
      val statId: Long
      val pokemonId: Long
}