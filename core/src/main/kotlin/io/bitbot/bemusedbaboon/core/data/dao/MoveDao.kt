package io.bitbot.bemusedbaboon.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.bitbot.bemusedbaboon.core.data.entity.move.Move

@Dao
interface MoveDao : BaseDao<Move, Move.Seed> {

    @Insert(entity = Move::class)
    override fun insertSeed(seed: Move.Seed): Long

    @Query("SELECT * FROM Move WHERE moveId IS :id")
    override fun getById(id: Long): Move
}