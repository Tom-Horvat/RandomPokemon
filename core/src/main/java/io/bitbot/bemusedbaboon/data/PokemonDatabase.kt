package io.bitbot.bemusedbaboon.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.bitbot.bemusedbaboon.data.dao.AbilityDao
import io.bitbot.bemusedbaboon.data.dao.CryDao
import io.bitbot.bemusedbaboon.data.dao.MoveDao
import io.bitbot.bemusedbaboon.data.dao.PokemonDao
import io.bitbot.bemusedbaboon.data.dao.SpeciesDao
import io.bitbot.bemusedbaboon.data.dao.SpritesDao
import io.bitbot.bemusedbaboon.data.dao.StatDao
import io.bitbot.bemusedbaboon.data.dao.StatSlotDao
import io.bitbot.bemusedbaboon.data.dao.TypeDao
import io.bitbot.bemusedbaboon.data.dao.TypeSlotDao
import io.bitbot.bemusedbaboon.data.entity.DateConverter
import io.bitbot.bemusedbaboon.data.entity.ability.Ability
import io.bitbot.bemusedbaboon.data.entity.cries.Cries
import io.bitbot.bemusedbaboon.data.entity.move.Move
import io.bitbot.bemusedbaboon.data.entity.pokemon.Pokemon
import io.bitbot.bemusedbaboon.data.entity.pokemon.PokemonAbility
import io.bitbot.bemusedbaboon.data.entity.pokemon.PokemonDaoOld
import io.bitbot.bemusedbaboon.data.entity.pokemon.PokemonDto
import io.bitbot.bemusedbaboon.data.entity.pokemon.PokemonMove
import io.bitbot.bemusedbaboon.data.entity.species.Species
import io.bitbot.bemusedbaboon.data.entity.sprites.Sprites
import io.bitbot.bemusedbaboon.data.entity.stat.Stat
import io.bitbot.bemusedbaboon.data.entity.stat.slot.StatSlot
import io.bitbot.bemusedbaboon.data.entity.type.Type
import io.bitbot.bemusedbaboon.data.entity.type.slot.TypeSlot

/**
 * A Room database for storing Pokemon data
 */
@Database(
    entities = [
        PokemonDto::class,
        ////////////////////////
        Ability::class,
        Cries::class,
        Move::class,
        Pokemon::class,
        PokemonAbility::class,
        PokemonMove::class,
        Species::class,
        Sprites::class,
        StatSlot::class,
        Stat::class,
        Type::class,
        TypeSlot::class
    ], version = 1
)
@TypeConverters(DateConverter::class)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDaoOld(): PokemonDaoOld

    abstract fun abilityDao(): AbilityDao
    abstract fun cryDao(): CryDao
    abstract fun moveDao(): MoveDao
    abstract fun pokemonDao(): PokemonDao
    abstract fun speciesDao(): SpeciesDao
    abstract fun spritesDao(): SpritesDao
    abstract fun statDao(): StatDao
    abstract fun statSlotDao(): StatSlotDao
    abstract fun typeDao(): TypeDao
    abstract fun typeSlotDao(): TypeSlotDao
}