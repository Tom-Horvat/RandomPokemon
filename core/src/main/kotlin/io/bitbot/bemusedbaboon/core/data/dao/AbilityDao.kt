package io.bitbot.bemusedbaboon.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.bitbot.bemusedbaboon.core.data.entity.ability.Ability

@Dao
interface AbilityDao : BaseDao<Ability> {

    @Insert(entity = Ability::class)
    override fun insert(seed: Ability): Long

    @Query("SELECT * FROM Ability WHERE abilityId IS :id")
    override fun getById(id: Long): Ability
}