package io.bitbot.bemusedbaboon.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.bitbot.bemusedbaboon.data.entity.sprites.Sprites

@Dao
interface SpritesDao : BaseDao<Sprites, Sprites.Seed> {

    @Insert(entity = Sprites::class)
    override fun insertSeed(seed: Sprites.Seed): Long

    @Query("SELECT * FROM Sprites WHERE pokemonId IS :id")
    override fun getById(id: Long): Sprites
}