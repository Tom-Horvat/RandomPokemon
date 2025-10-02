package io.bitbot.bemusedbaboon.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.bitbot.bemusedbaboon.data.entity.ability.Ability

@Dao
interface AbilityDao : BaseDao<Ability, Ability.Seed> {

    @Insert(entity = Ability::class)
    override fun insertSeed(seed: Ability.Seed): Long

    @Query("SELECT * FROM Ability WHERE abilityId IS :id")
    override fun getById(id: Long): Ability
}