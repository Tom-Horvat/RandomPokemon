package io.bitbot.bemusedbaboon.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.bitbot.bemusedbaboon.core.data.entity.stat.Stat

@Dao
interface StatDao : BaseDao<Stat> {

    @Insert(entity = Stat::class)
    override fun insert(seed: Stat): Long

    @Query("SELECT * FROM Stat WHERE statId IS :id")
    override fun getById(id: Long): Stat
}