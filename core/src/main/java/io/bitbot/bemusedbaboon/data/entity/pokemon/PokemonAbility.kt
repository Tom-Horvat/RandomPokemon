package io.bitbot.bemusedbaboon.data.entity.pokemon

import androidx.room.Entity

@Entity(primaryKeys = ["pokemonId", "abilityId"])
data class PokemonAbility (
    val pokemonId: Long,
    val abilityId: Long,
)