package io.bitbot.bemusedbaboon.core.data.entity.move

import io.bitbot.bemusedbaboon.core.data.entity.Timestamp

sealed interface Props : Timestamp {
    val moveId: Long
    val name: String
}