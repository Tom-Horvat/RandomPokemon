package io.bitbot.bemusedbaboon.common.api.data

import io.bitbot.bemusedbaboon.core.data.entity.ability.slot.AbilitySlot
import io.bitbot.bemusedbaboon.core.data.entity.cries.Cries
import io.bitbot.bemusedbaboon.core.data.entity.pokemon.Pokemon
import io.bitbot.bemusedbaboon.core.data.entity.sprites.Sprites
import io.bitbot.bemusedbaboon.core.domain.model.PokemonDetails

data class PokemonDto(
    val pokemon: Pokemon,
    val cries: Cries,
    val sprites: Sprites,
    val abilities: List<AbilitySlot>
)

fun PokemonDto.toDomainModel() = PokemonDetails(
    pokemon = pokemon,
    cries = cries,
    sprites = sprites,
    abilities = abilities
)