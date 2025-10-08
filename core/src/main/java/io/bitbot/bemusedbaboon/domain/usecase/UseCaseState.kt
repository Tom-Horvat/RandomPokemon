package io.bitbot.bemusedbaboon.domain.usecase

sealed class UseCaseState {
    data class Running(val message: String? = null) :
        UseCaseState()

    data class Done<out O>(val output: O?) : UseCaseState()
    data class Error<out I>(val error: Throwable?, val input: I?) : UseCaseState()

    inline fun <reified T, reified R> parseUseCase(
        noinline onRunning: (String?) -> Unit = {},
        onError: (Throwable?, T?) -> Unit = { _, _ -> },
        onDone: (R?) -> Unit = {},
    ) {
        when (this) {
            is Running -> onRunning(message)
            is Error<*> -> {
                onError(
                    this.error,
                    this.input.takeIf { it is T? }?.let { it as T? } ?: run { null })
            }

            is Done<*> -> this.output?.let {
                it.takeIf { it is R }?.let { result -> onDone(result as R?) }
                    ?: run {
                        onError(
                            IllegalArgumentException("Expected output of type ${R::class.simpleName}; got ${output?.let { it::class.simpleName }}"),
                            null
                        )
                    }
            } ?: run { onDone(null) }
        }
    }
}