package io.bitbot.bemusedbaboon.core.data.entity.type

import io.bitbot.bemusedbaboon.core.data.entity.Timestamp

sealed interface Props : Timestamp {
       val typeId: Long
       val name: String
}