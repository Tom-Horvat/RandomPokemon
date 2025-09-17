package io.bitbot.bemusedbaboon.framework.provider

import io.bitbot.bemusedbaboon.data.database.PokemonDao
import io.bitbot.bemusedbaboon.framework.PokemonDatabase

/**
 * Provides the [PokemonDao].
 *
 * @param database the [PokemonDatabase]
 */
fun provideDao(database: PokemonDatabase): PokemonDao = database.pokemonDao()