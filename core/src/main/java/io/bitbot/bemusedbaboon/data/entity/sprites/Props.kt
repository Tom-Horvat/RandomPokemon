package io.bitbot.bemusedbaboon.data.entity.sprites

import io.bitbot.bemusedbaboon.data.entity.DbEntry

interface Props : DbEntry {
    val pokemonId: Long
    val backDefault: String
    val backFemale: String?
    val backShiny: String
    val backShinyFemale: String?
    val frontDefault: String
    val frontFemale: String?
    val fronShiny: String
    val frontShinyFemale: String?
}