package io.bitbot.bemusedbaboon.framework

import io.bitbot.bemusedbaboon.domain.PokeIndex

/**
 * An interface for storing the PokeIndex in the SharedPreferences
 */
interface PrefsCache {
    fun savePokeIndex(pokeIndex: PokeIndex)
    fun getPokeIndex(): PokeIndex?
}