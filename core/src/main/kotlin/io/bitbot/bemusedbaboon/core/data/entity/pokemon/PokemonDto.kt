package io.bitbot.bemusedbaboon.core.data.entity.pokemon

import io.bitbot.bemusedbaboon.core.data.entity.ability.slot.AbilitySlot
import io.bitbot.bemusedbaboon.core.data.entity.cries.Cries
import io.bitbot.bemusedbaboon.core.data.entity.sprites.Sprites

data class PokemonDto(
    val pokemon: Pokemon,
    val cries: Cries,
    val sprites: Sprites,
    val abilities: List<AbilitySlot>
)