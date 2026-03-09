package io.bitbot.bemusedbaboon.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.bitbot.bemusedbaboon.core.data.entity.type.Type

@Dao
interface TypeDao : BaseDao<Type> {

    @Insert(entity = Type::class)
    override fun insert(seed: Type): Long

    @Query("SELECT * FROM Type WHERE typeId IS :id")
    override fun getById(id: Long): Type
}