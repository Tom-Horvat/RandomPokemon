package io.bitbot.bemusedbaboon.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import io.bitbot.bemusedbaboon.core.data.entity.stat.slot.StatSlot

@Dao
interface StatSlotDao : BaseDao<StatSlot> {

    @Insert(entity = StatSlot::class)
    override fun insert(seed: StatSlot): Long

    @Query("SELECT * FROM StatSlot WHERE statSlotId IS :id")
    override fun getById(id: Long): StatSlot

    @Transaction
    @Query("SELECT * FROM StatSlot WHERE statSlotId IS :id")
    fun getStatSlotWithStat(id: Long): StatSlot.Complete
}