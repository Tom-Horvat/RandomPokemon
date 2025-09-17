package io.bitbot.bemusedbaboon.domain

/**
 * A class representing the pokemon index.
 */
data class PokeIndex(
    val count: Int,
    val indexes: List<Int>,
    val lastRefreshed: Long,
)
