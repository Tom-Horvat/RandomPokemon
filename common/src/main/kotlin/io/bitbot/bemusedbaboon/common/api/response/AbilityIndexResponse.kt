package io.bitbot.bemusedbaboon.common.api.response

import com.squareup.moshi.Json
import io.bitbot.bemusedbaboon.common.api.PokemonApi
import io.bitbot.bemusedbaboon.common.api.stripUrlPrefix
import io.bitbot.bemusedbaboon.core.data.entity.ability.slot.AbilitySlot

data class AbilityIndexResponse(
    val ability: NamedUrl,
    @param:Json(name = "is_hidden")
    val isHidden: Boolean,
    val slot: Int
)

fun List<AbilityIndexResponse>.toEntities(pokemonId: Long) =
    map { it.toEntity(pokemonId = pokemonId) }

fun AbilityIndexResponse.toEntity(pokemonId: Long) = AbilitySlot(
    pokemonId = pokemonId,
    slot = slot,
    isHidden = isHidden,
    abilityId = ability.url.stripUrlPrefix(PokemonApi.ABILITY)
)