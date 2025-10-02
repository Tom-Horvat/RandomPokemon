package io.bitbot.bemusedbaboon.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.bitbot.bemusedbaboon.data.entity.stat.Stat

@Dao
interface StatDao : BaseDao<Stat, Stat.Seed> {

    @Insert(entity = Stat::class)
    override fun insertSeed(seed: Stat.Seed): Long

    @Query("SELECT * FROM Stat WHERE statId IS :id")
    override fun getById(id: Long): Stat
}