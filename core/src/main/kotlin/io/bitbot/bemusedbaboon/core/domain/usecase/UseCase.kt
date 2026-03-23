package io.bitbot.bemusedbaboon.core.domain.usecase

import io.bitbot.bemusedbaboon.core.domain.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class UseCase<in O>(private val logger: Logger? = null) {

    abstract val name: String
    private val _state = MutableStateFlow<ResultState?>(null)
    val state: StateFlow<ResultState?> = _state

    protected fun Throwable?.update() {
        logger?.e(this ?: Throwable(), "Error in $name")
        _state.value = ResultState.Error(error = this)
    }

    protected fun running(message: String? = null) {
        logger?.d("Running $name: $message")
        _state.value = ResultState.Running(message = message)
    }

    protected fun O?.update() {
        logger?.d("Completed $name: $this")
        _state.value = ResultState.Complete<O>(data = this)
    }
}
