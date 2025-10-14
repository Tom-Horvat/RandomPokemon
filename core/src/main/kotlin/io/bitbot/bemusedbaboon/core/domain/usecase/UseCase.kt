package io.bitbot.bemusedbaboon.core.domain.usecase

import io.bitbot.bemusedbaboon.core.domain.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class UseCase<in O> {
    private val _state = MutableStateFlow<ResultState?>(null)
    val state: StateFlow<ResultState?> = _state

    protected fun Throwable?.update() {
        _state.value = ResultState.Error(error = this)
    }

    protected fun running(message: String? = null) {
        _state.value = ResultState.Running(message = message)
    }

    protected fun O?.update() {
        _state.value = ResultState.Complete<O>(data = this)
    }
}
