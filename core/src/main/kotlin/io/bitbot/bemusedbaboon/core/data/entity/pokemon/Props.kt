package io.bitbot.bemusedbaboon.core.data.entity.pokemon

import io.bitbot.bemusedbaboon.core.data.entity.Timestamp

sealed interface Props : Timestamp {
    val pokemonId: Long
    val baseExperience: Int
    val height: Int
    val name: String
    val locationEncounters: String
    val weight: Int
}