package io.bitbot.bemusedbaboon.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.bitbot.bemusedbaboon.core.data.entity.species.Species

@Dao
interface SpeciesDao : BaseDao<Species> {

    @Insert(entity = Species::class)
    override fun insert(seed: Species): Long

    @Query("SELECT * FROM Species WHERE speciesId IS :id")
    override fun getById(id: Long): Species
}