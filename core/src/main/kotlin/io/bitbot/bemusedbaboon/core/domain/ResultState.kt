package io.bitbot.bemusedbaboon.core.domain

/**
 * The result state of an operation.
 */
sealed class ResultState {
    /**
     * The operation completed.
     *
     * @param data The return result of the operation, if any.
     */
    data class Complete<out O>(val data: O?) : ResultState()

    /**
     * The operation is running.
     *
     * @param message The message provided while the operation is executing.
     */
    data class Running(val message: String? = null) : ResultState()

    /**
     * The operation failed.
     *
     * @param error The error thrown by executing the operation.
     */
    data class Error(val error: Throwable? = null) : ResultState()

    /**
     * Parses the [ResultState] and runs the given lambdas for each result state type.
     *
     * @param onRunning Execute while loading, provides a message if any.
     * @param onError Execute on received error.
     * @param onComplete Execute on operation completed. Validates that the passed data is of the correct type.
     *
     * @throws IllegalArgumentException when the data type does not match the expected one.
     */
    inline fun <reified O> parse(
        onRunning: (String?) -> Unit = {},
        onError: (Throwable?) -> Unit = {},
        onComplete: (O?) -> Unit = {},
    ) {
        when (this) {
            is Running -> onRunning(this.message)
            is Error -> onError(this.error)
            is Complete<*> -> this.data?.let {
                it.takeIf { it is O }?.let { result -> onComplete(result as O?) }
                    ?: run { onError(IllegalArgumentException("Expected output of type ${O::class.simpleName}; got ${data?.let { data::class.simpleName }}")) }
            } ?: run { onComplete(null) }
        }
    }
}