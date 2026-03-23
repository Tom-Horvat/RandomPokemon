package io.bitbot.bemusedbaboon.core.domain.usecase.index

import io.bitbot.bemusedbaboon.core.data.repository.index.IndexRepo
import io.bitbot.bemusedbaboon.core.data.repository.index.InitializationProvider
import io.bitbot.bemusedbaboon.core.domain.usecase.Logger
import io.bitbot.bemusedbaboon.core.domain.usecase.UseCase
import kotlinx.coroutines.flow.first

class GetRandomIndex(
    logger: Logger? = null,
    private val initializationProvider: InitializationProvider,
    private val local: IndexRepo.Local,
) : UseCase<Long>(logger) {
    override val name: String = "GetRandomIndex"
    suspend operator fun invoke() {
        initializationProvider.isInitialized.first { isReady -> isReady }
        local.getRandomIndex().collect { result ->
            result.parse<Long>(
                onRunning = { running(it) },
                onError = { e -> e.update() }
            ) { it.update() }
        }
    }
}