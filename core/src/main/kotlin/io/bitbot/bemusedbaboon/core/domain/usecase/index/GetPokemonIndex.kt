package io.bitbot.bemusedbaboon.core.domain.usecase.index

import io.bitbot.bemusedbaboon.core.data.entity.index.Index
import io.bitbot.bemusedbaboon.core.data.repository.index.IndexRepo
import io.bitbot.bemusedbaboon.core.domain.usecase.Logger
import io.bitbot.bemusedbaboon.core.domain.usecase.UseCase

class GetPokemonIndex(logger: Logger, private val repo: IndexRepo) : UseCase<List<Index>>(logger) {
    override val name: String = "GetPokemonIndex"

    suspend operator fun invoke(count: Int) {
        repo.getPokemonIndex(count).collect { result ->
            result.parse<List<Index>>(
                onRunning = { running(it) },
                onError = { e -> e.update() }
            ) { index -> index.update() }
        }
    }
}