package io.bitbot.bemusedbaboon.core.domain.usecase.index

import io.bitbot.bemusedbaboon.core.data.repository.index.IndexRepo
import io.bitbot.bemusedbaboon.core.domain.usecase.UseCase

class GetRandomIndex(private val local: IndexRepo.Local) : UseCase<Long>() {
    suspend operator fun invoke() {
        local.getRandomIndex().collect { result ->
            result.parse<Long>(
                onRunning = { running() },
                onError = { e -> e.update() }
            ) { index ->
                index?.update()
            }
        }
    }
}