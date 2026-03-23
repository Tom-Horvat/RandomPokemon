package io.bitbot.bemusedbaboon.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.bitbot.bemusedbaboon.core.data.entity.sprites.Sprites

@Dao
interface SpritesDao : BaseDao<Sprites> {

    @Insert(entity = Sprites::class)
    override fun insert(seed: Sprites): Long

    @Query("SELECT * FROM Sprites WHERE pokemonId IS :id")
    override fun getById(id: Long): Sprites
}