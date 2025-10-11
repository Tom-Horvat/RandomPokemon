package io.bitbot.bemusedbaboon.core.domain.usecase

sealed class UseCaseState {
    data class Running(val message: String? = null) :
        UseCaseState()

    data class Done<out O>(val output: O?) : UseCaseState()
    data class Error(val error: Throwable?) : UseCaseState()

    inline fun <reified T, reified R> parse(
        noinline onRunning: (String?) -> Unit = {},
        onError: (Throwable?) -> Unit = {},
        onDone: (R?) -> Unit = {},
    ) {
        when (this) {
            is Running -> onRunning(message)
            is Error -> {
                onError(this.error)
            }

            is Done<*> -> this.output?.let {
                it.takeIf { it is R }?.let { result -> onDone(result as R?) }
                    ?: run {
                        onError(
                            IllegalArgumentException("Expected output of type ${R::class.simpleName}; got ${output?.let { o -> o::class.simpleName }}"),
                        )
                    }
            } ?: run { onDone(null) }
        }
    }
}