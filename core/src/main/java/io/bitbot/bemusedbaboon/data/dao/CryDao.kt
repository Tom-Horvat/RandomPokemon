package io.bitbot.bemusedbaboon.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.bitbot.bemusedbaboon.data.entity.cries.Cries

@Dao
interface CryDao : BaseDao<Cries, Cries.Seed> {

    @Insert(entity = Cries::class)
    override fun insertSeed(seed: Cries.Seed): Long

    @Query("SELECT * FROM Cry WHERE cryId IS :id")
    override fun getById(id: Long): Cries
}