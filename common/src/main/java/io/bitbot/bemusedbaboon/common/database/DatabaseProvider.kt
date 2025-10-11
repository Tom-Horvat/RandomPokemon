package io.bitbot.bemusedbaboon.common.database

import android.content.Context
import androidx.room.Room
import io.bitbot.bemusedbaboon.core.data.PokemonDatabase

fun provideDatabase(context: Context) =
    Room.databaseBuilder(context, PokemonDatabase::class.java, "db").build()