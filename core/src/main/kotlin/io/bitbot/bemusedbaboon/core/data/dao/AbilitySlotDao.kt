package io.bitbot.bemusedbaboon.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.bitbot.bemusedbaboon.core.data.entity.ability.slot.AbilitySlot

@Dao
interface AbilitySlotDao : BaseDao<AbilitySlot> {

    @Insert(entity = AbilitySlot::class)
    override fun insert(seed: AbilitySlot): Long

    @Insert
    fun insertAll(abilitySlots: List<AbilitySlot>): List<Long>

    @Query("SELECT * FROM AbilitySlot WHERE abilityId IS :id")
    override fun getById(id: Long): AbilitySlot
}