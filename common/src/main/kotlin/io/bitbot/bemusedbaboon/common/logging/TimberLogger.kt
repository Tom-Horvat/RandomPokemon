package io.bitbot.bemusedbaboon.common.logging

import io.bitbot.bemusedbaboon.core.domain.usecase.Logger
import timber.log.Timber

class TimberLogger : Logger {
    override fun d(message: String) {
        Timber.d(message)
    }

    override fun i(message: String) {
        Timber.i(message)
    }

    override fun e(throwable: Throwable, message: String) {
        Timber.e(throwable, message)
    }
}
