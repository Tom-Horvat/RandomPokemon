package io.bitbot.bemusedbaboon.common.api.response

import com.squareup.moshi.Json
import io.bitbot.bemusedbaboon.core.data.entity.sprites.Sprites

data class SpritesResponse(
    @param:Json(name = "back_default")
    val backDefault: String?,
    @param:Json(name = "back_female")
    val backFemale: String?,
    @param:Json(name = "back_shiny")
    val backShiny: String?,
    @param:Json(name = "back_shiny_female")
    val backShinyFemale: String?,
    @param:Json(name = "front_default")
    val frontDefault: String,
    @param:Json(name = "front_female")
    val frontFemale: String?,
    @param:Json(name = "front_shiny")
    val frontShiny: String?,
    @param:Json(name = "front_shiny_female")
    val frontShinyFemale: String?
)

fun SpritesResponse.toEntity(id: Long) = Sprites(
    pokemonId = id,
    backDefault = backDefault,
    backFemale = backFemale,
    backShiny = backShiny,
    backShinyFemale = backShinyFemale,
    frontDefault = frontDefault,
    fronShiny = frontShiny,
    frontFemale = frontFemale,
    frontShinyFemale = frontShinyFemale,
)
