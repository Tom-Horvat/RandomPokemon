package io.bitbot.scoreboard.core.data.repository

/**
 * Represents a result of a repository function call.
 */
sealed class Result {
    /**
     * A successful result, containing received data or null.
     *
     * @property data a nullable result from the call.
     */
    data class Success<out O>(val data: O?) : Result()

    /**
     * A loading result, signalling a pending response from a call.
     */
    data object Loading : Result()

    /**
     * A canceled result, signalling an operation was terminated without error or a return result.
     */
    data object Cancelled : Result()

    /**
     * An error result.
     *
     * @property throwable a nullable error received from a call.
     */
    data class Error(val throwable: Throwable? = null) : Result()

    /**
     * Parses the [Result] and runs the given lambdas for each result.
     *
     * @param onLoading do on loading, does not return any message or data.
     * @param onCancelled do on cancel, does not return any message or data.
     * @param onError do on error, returns a nullable [Throwable].
     * @param onSuccess do on success, returns an error if the resulting data class does not match
     * the given type, otherwise returns the resulting data.
     */
    inline fun <reified O> parseApiResult(
        onLoading: () -> Unit = {},
        onCancelled: () -> Unit = {},
        onError: (Throwable?) -> Unit = {},
        onSuccess: (O?) -> Unit = {},
    ) {
        when (this) {
            is Loading -> onLoading()
            is Cancelled -> onCancelled()
            is Error -> onError(this.throwable)
            is Success<*> -> this.data?.let {
                it.takeIf { it is O }?.let { result -> onSuccess(result as O?) }
                    ?: run { onError(IllegalArgumentException("Expected output of type ${O::class.simpleName}; got ${data?.let { it::class.simpleName }}")) }
            } ?: run { onSuccess(null) }
        }
    }
}