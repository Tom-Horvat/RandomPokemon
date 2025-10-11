package io.bitbot.bemusedbaboon.core.data.entity.sprites

import io.bitbot.bemusedbaboon.core.data.entity.Timestamp

interface Props : Timestamp {
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