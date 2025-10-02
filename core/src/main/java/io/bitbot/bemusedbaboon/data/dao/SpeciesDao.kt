package io.bitbot.bemusedbaboon.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.bitbot.bemusedbaboon.data.entity.species.Species

@Dao
interface SpeciesDao : BaseDao<Species, Species.Seed> {

    @Insert(entity = Species::class)
    override fun insertSeed(seed: Species.Seed): Long

    @Query("SELECT * FROM Species WHERE speciesId IS :id")
    override fun getById(id: Long): Species
}