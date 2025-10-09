package io.bitbot.bemusedbaboon.data.repository

import io.bitbot.scoreboard.core.data.repository.Result
import kotlinx.coroutines.flow.flow

abstract class ResultFlowConverter {
    fun <O>toResultFlow(execute: suspend () -> O) = flow {
        emit(Result.Loading)
        try {
            emit(Result.Success(execute()))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}