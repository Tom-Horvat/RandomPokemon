package io.bitbot.bemusedbaboon.common.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.bitbot.bemusedbaboon.core.data.entity.pokemon.Pokemon
import io.bitbot.bemusedbaboon.common.api.data.PokemonDto

@JsonClass(generateAdapter = true)
data class PokemonResponse(
    val abilities: List<AbilityIndexResponse>,
    @param:Json(name = "base_experience")
    val baseExperience: Int,
    val cries: CriesResponse,
    val height: Int,
    val id: Long,
    @param:Json(name = "location_area_encounters")
    val locationEncounters: String,
    val moves: List<MoveIndexResponse>,
    val name: String,
    val species: NamedUrl,
    val sprites: SpritesResponse,
    val stats: List<StatResponse>,
    val types: List<TypeResponse>,
    val weight: Int,
)

fun PokemonResponse.toEntity() = Pokemon(
    pokemonId = id,
    baseExperience = baseExperience,
    height = height,
    name = name,
    locationEncounters = locationEncounters,
    weight = weight
)

fun PokemonResponse.toDto() = PokemonDto(
    pokemon = toEntity(),
    cries = cries.toEntity(id),
    sprites = sprites.toEntity(id),
    abilities = abilities.toEntities(id)
)
