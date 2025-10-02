package io.bitbot.bemusedbaboon.data.entity.ability

import io.bitbot.bemusedbaboon.data.entity.DbEntry

sealed interface Props : DbEntry {
    val abilityId: Long
    val name: String
}