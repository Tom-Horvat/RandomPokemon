package io.bitbot.bemusedbaboon.framework

import androidx.room.Database
import androidx.room.RoomDatabase
import io.bitbot.bemusedbaboon.data.database.PokemonDao
import io.bitbot.bemusedbaboon.data.database.PokemonDto

/**
 * A Room database for storing Pokemon data
 */
@Database(entities = [PokemonDto::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {
    companion object {
        const val POKEMON_DB = "pokemon_database"
    }

    abstract fun pokemonDao(): PokemonDao
}