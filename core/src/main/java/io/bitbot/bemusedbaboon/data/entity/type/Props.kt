package io.bitbot.bemusedbaboon.data.entity.type

import io.bitbot.bemusedbaboon.data.entity.DbEntry

sealed interface Props : DbEntry {
       val typeId: Long
       val name: String
}