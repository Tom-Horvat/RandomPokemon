package io.bitbot.bemusedbaboon.core.domain.usecase.index

import io.bitbot.bemusedbaboon.core.data.entity.pokemon.Pokemon
import io.bitbot.bemusedbaboon.core.data.entity.pokemon.PokemonDto
import io.bitbot.bemusedbaboon.core.data.repository.pokemon.PokemonRepo
import io.bitbot.bemusedbaboon.core.domain.usecase.UseCase
import kotlinx.coroutines.flow.combine

class GetPokemon(private val local: PokemonRepo.Local, private val remote: PokemonRepo) :
    UseCase<Pokemon.Complete>() {
    suspend operator fun invoke(id: Long) {
        remote.getPokemonById(id).collect { result ->
            result.parse<PokemonDto>(
                onRunning = { running("Fetching pokemon...") },
                onError = { e -> e.update() }
            ) { dto ->
                dto?.let {
                    combine(
                        local.savePokemon(dto.pokemon),
                        local.saveCries(dto.cries),
                        local.saveSprites(dto.sprites),
                        local.saveAbilities(dto.abilities)
                    ) { pokemonId, criesId, spritesId, abilityCount ->
                        mapOf(
                            "pokemonId" to pokemonId,
                            "criesId" to criesId,
                            "spritesId" to spritesId,
                            "abilityCount" to abilityCount
                        )
                    }.collect { result ->
                        result["pokemonId"]?.parse<Long> { id ->
                            id?.let {
                                local.getPokemonById(it).collect { pokemonResult ->
                                    pokemonResult.parse<Pokemon.Complete> { pokemon ->
                                        pokemon.update()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}