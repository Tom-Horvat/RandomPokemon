package io.bitbot.bemusedbaboon.core.data.entity.stat

import io.bitbot.bemusedbaboon.core.data.entity.Timestamp

sealed interface Props : Timestamp {
     val statId: Long
     val isBattleOnly: Boolean
     val name: String
}