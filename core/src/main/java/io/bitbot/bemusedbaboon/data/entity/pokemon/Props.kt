package io.bitbot.bemusedbaboon.data.entity.pokemon

import io.bitbot.bemusedbaboon.data.entity.DbEntry

sealed interface Props : DbEntry {
    val pokemonId: Long
    val baseExperience: Int
    val height: Int
    val name: String
    val locationEncounters: String
    val weight: Int
}