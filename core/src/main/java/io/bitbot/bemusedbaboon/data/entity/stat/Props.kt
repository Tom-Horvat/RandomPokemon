package io.bitbot.bemusedbaboon.data.entity.stat

import io.bitbot.bemusedbaboon.data.entity.DbEntry

sealed interface Props : DbEntry {
     val statId: Long
     val isBattleOnly: Boolean
     val name: String
}