package io.bitbot.bemusedbaboon.data.api

/**
 * An interface for use with DTOs to convert them to given classes
 */
interface Dto<out I> {
    fun toObject(): I
}