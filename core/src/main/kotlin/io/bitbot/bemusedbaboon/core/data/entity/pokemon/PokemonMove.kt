package io.bitbot.bemusedbaboon.core.data.entity.pokemon

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["pokemonId", "moveId"])
data class PokemonMove (
    @ColumnInfo(index = true)
    val pokemonId: Long,
    val moveId: Long,
)