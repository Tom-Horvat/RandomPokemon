package io.bitbot.bemusedbaboon.core.domain.usecase.pokemon

import io.bitbot.bemusedbaboon.core.data.repository.pokemon.PokemonRepo
import io.bitbot.bemusedbaboon.core.domain.model.PokemonDetails
import io.bitbot.bemusedbaboon.core.domain.usecase.Logger
import io.bitbot.bemusedbaboon.core.domain.usecase.UseCase

class GetPokemon(
    logger: Logger,
    private val repo: PokemonRepo
) : UseCase<PokemonDetails>(logger) {
    override val name: String = "GetPokemon"
    suspend operator fun invoke(id: Long) {
        repo.getPokemonById(id).collect { result ->
            result.parse<PokemonDetails>(
                onRunning = { running(it) },
                onError = { e -> e.update() }
            ) { it.update() }
        }
    }
}