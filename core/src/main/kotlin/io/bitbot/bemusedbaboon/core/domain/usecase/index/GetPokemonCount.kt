package io.bitbot.bemusedbaboon.core.domain.usecase.index

import io.bitbot.bemusedbaboon.core.data.repository.index.IndexRepo
import io.bitbot.bemusedbaboon.core.domain.usecase.Logger
import io.bitbot.bemusedbaboon.core.domain.usecase.UseCase

class GetPokemonCount(logger: Logger, private val repo: IndexRepo) : UseCase<Int>(logger) {
    override val name: String = "GetPokemonCount"

    suspend operator fun invoke() {
        repo.getPokemonCount().collect { result ->
            result.parse<Int>(
                onRunning = { m -> running(m) },
                onError = { e -> e.update() }
            ) { count -> count.update() }
        }
    }
}