package io.bitbot.bemusedbaboon.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.bitbot.bemusedbaboon.core.data.entity.cries.Cries

@Dao
interface CriesDao : BaseDao<Cries> {

    @Insert(entity = Cries::class)
    override fun insert(seed: Cries): Long

    @Query("SELECT * FROM Cries WHERE pokemonId IS :id")
    override fun getById(id: Long): Cries
}