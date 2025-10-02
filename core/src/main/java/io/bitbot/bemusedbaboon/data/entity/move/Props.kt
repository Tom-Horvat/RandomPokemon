package io.bitbot.bemusedbaboon.data.entity.move

import io.bitbot.bemusedbaboon.data.entity.DbEntry

sealed interface Props : DbEntry {
    val moveId: Long
    val name: String
}