package io.bitbot.bemusedbaboon.core.data.entity.pokemon

import androidx.room.Entity

@Entity(primaryKeys = ["pokemonId", "moveId"])
data class PokemonMove (
    val pokemonId: Long,
    val moveId: Long,
)