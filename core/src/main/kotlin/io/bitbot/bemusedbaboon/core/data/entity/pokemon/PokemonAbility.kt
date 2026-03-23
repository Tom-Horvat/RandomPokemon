package io.bitbot.bemusedbaboon.core.data.entity.pokemon

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["pokemonId", "abilityId"])
data class PokemonAbility (
    @ColumnInfo(index = true)
    val pokemonId: Long,
    val abilityId: Long,
)