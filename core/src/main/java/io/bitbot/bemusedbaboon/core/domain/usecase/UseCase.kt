package io.bitbot.bemusedbaboon.core.domain.usecase

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class UseCase<in O> {
    private val _state = MutableStateFlow<UseCaseState?>(null)
    val state: StateFlow<UseCaseState?> = _state

    protected fun Throwable?.update(){
        _state.value = UseCaseState.Error(error = this)
    }

    protected fun running(message: String? = null) {
        _state.value = UseCaseState.Running(message = message)
    }

    protected fun O?.update() {
        _state.value = UseCaseState.Done<O>(output = this)
    }
}
