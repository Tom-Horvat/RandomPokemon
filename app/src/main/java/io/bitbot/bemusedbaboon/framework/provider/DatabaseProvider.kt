package io.bitbot.bemusedbaboon.framework.provider

import android.content.Context
import androidx.room.Room
import io.bitbot.bemusedbaboon.framework.PokemonDatabase

/**
 * Provides a [PokemonDatabase] instance.
 *
 * @param context
 */
fun provideDatabase(context: Context): PokemonDatabase = Room
    .databaseBuilder(
        context.applicationContext,
        PokemonDatabase::class.java,
        PokemonDatabase.Companion.POKEMON_DB
    )
    .build()