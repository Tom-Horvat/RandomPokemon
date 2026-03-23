package io.bitbot.bemusedbaboon.core.domain.model

import io.bitbot.bemusedbaboon.core.data.entity.ability.slot.AbilitySlot
import io.bitbot.bemusedbaboon.core.data.entity.cries.Cries
import io.bitbot.bemusedbaboon.core.data.entity.pokemon.Pokemon
import io.bitbot.bemusedbaboon.core.data.entity.sprites.Sprites

/**
 * Represents the complete details of a Pokemon for the domain and UI layers.
 * This class is agnostic of the data source (network or database).
 */
data class PokemonDetails (
    val pokemon: Pokemon,
    val cries: Cries,
    val sprites: Sprites,
    val abilities: List<AbilitySlot>
)