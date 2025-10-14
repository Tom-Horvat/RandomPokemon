package io.bitbot.bemusedbaboon.core.data.entity.ability

import io.bitbot.bemusedbaboon.core.data.entity.Timestamp

sealed interface Props : Timestamp {
    val abilityId: Long
    val name: String
}