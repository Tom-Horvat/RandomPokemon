package io.bitbot.bemusedbaboon.core.data.entity.ability.slot

import io.bitbot.bemusedbaboon.core.data.entity.Timestamp

interface Props: Timestamp {
    val abilityId: Long
    val pokemonId: Long
    val slot: Int
}