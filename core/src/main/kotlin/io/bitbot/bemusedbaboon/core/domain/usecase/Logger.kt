package io.bitbot.bemusedbaboon.core.domain.usecase

interface Logger {
    fun d(message: String)
    fun i(message: String)
    fun e(throwable: Throwable, message: String)
}
