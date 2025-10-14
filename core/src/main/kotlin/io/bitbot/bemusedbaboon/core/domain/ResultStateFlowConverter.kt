package io.bitbot.bemusedbaboon.core.domain

import kotlinx.coroutines.flow.flow

/**
 * A converter for returning any type of data as a [ResultState] to be consumed by use cases.
 */
abstract class ResultStateFlowConverter {
    /**
     * Creates a flow that emits changes in the given [ResultState] as it executes the given
     * operation.
     */
    fun <O>toResultStateFlow(execute: suspend () -> O) = flow {
        emit(ResultState.Running())
        try {
            emit(ResultState.Complete(execute()))
        } catch (e: Exception) {
            emit(ResultState.Error(e))
        }
    }
}