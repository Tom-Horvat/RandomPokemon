package io.bitbot.bemusedbaboon.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import io.bitbot.bemusedbaboon.data.entity.type.slot.TypeSlot

@Dao
interface TypeSlotDao : BaseDao<TypeSlot, TypeSlot.Seed> {

    @Insert(entity = TypeSlot::class)
    override fun insertSeed(seed: TypeSlot.Seed): Long

    @Query("SELECT * FROM TypeSlot WHERE typeSlotId IS :id")
    override fun getById(id: Long): TypeSlot

    @Transaction
    @Query("SELECT * FROM TypeSlot WHERE typeSlotId IS :id")
    fun getStatSlotWithStat(id: Long): TypeSlot.Complete
}