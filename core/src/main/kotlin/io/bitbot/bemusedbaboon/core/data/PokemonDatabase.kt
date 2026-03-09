package io.bitbot.bemusedbaboon.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.bitbot.bemusedbaboon.core.data.dao.AbilityDao
import io.bitbot.bemusedbaboon.core.data.dao.AbilitySlotDao
import io.bitbot.bemusedbaboon.core.data.dao.CriesDao
import io.bitbot.bemusedbaboon.core.data.dao.IndexDao
import io.bitbot.bemusedbaboon.core.data.dao.MoveDao
import io.bitbot.bemusedbaboon.core.data.dao.PokemonDao
import io.bitbot.bemusedbaboon.core.data.dao.SpeciesDao
import io.bitbot.bemusedbaboon.core.data.dao.SpritesDao
import io.bitbot.bemusedbaboon.core.data.dao.StatDao
import io.bitbot.bemusedbaboon.core.data.dao.StatSlotDao
import io.bitbot.bemusedbaboon.core.data.dao.TypeDao
import io.bitbot.bemusedbaboon.core.data.dao.TypeSlotDao
import io.bitbot.bemusedbaboon.core.data.entity.DateConverter
import io.bitbot.bemusedbaboon.core.data.entity.ability.Ability
import io.bitbot.bemusedbaboon.core.data.entity.ability.slot.AbilitySlot
import io.bitbot.bemusedbaboon.core.data.entity.cries.Cries
import io.bitbot.bemusedbaboon.core.data.entity.index.Index
import io.bitbot.bemusedbaboon.core.data.entity.move.Move
import io.bitbot.bemusedbaboon.core.data.entity.pokemon.Pokemon
import io.bitbot.bemusedbaboon.core.data.entity.pokemon.PokemonMove
import io.bitbot.bemusedbaboon.core.data.entity.species.Species
import io.bitbot.bemusedbaboon.core.data.entity.sprites.Sprites
import io.bitbot.bemusedbaboon.core.data.entity.stat.Stat
import io.bitbot.bemusedbaboon.core.data.entity.stat.slot.StatSlot
import io.bitbot.bemusedbaboon.core.data.entity.type.Type
import io.bitbot.bemusedbaboon.core.data.entity.type.slot.TypeSlot

/**
 * A Room database for storing Pokemon data
 */
@Database(
    entities = [
        Ability::class,
        AbilitySlot::class,
        Cries::class,
        Move::class,
        Pokemon::class,
        PokemonMove::class,
        Species::class,
        Sprites::class,
        StatSlot::class,
        Stat::class,
        Type::class,
        TypeSlot::class,
        Index::class
    ], version = 1
)
@TypeConverters(DateConverter::class)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun abilityDao(): AbilityDao
    abstract fun abilitySlotDao(): AbilitySlotDao
    abstract fun cryDao(): CriesDao
    abstract fun moveDao(): MoveDao
    abstract fun pokemonDao(): PokemonDao
    abstract fun speciesDao(): SpeciesDao
    abstract fun spritesDao(): SpritesDao
    abstract fun statDao(): StatDao
    abstract fun statSlotDao(): StatSlotDao
    abstract fun typeDao(): TypeDao
    abstract fun typeSlotDao(): TypeSlotDao
    abstract fun indexDao(): IndexDao
}