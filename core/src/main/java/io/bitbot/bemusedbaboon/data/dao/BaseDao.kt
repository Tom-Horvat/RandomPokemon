package io.bitbot.bemusedbaboon.data.dao

import androidx.room.Delete
import androidx.room.Update

interface BaseDao<E, S> {

    fun insertSeed(seed: S): Long

    fun getById(id: Long): E

    @Update
    fun update(vararg entity: E): Int

    @Delete
    fun delete(vararg entity: E): Int
}