package io.bitbot.bemusedbaboon.core.data.dao

import androidx.room.Delete
import androidx.room.Update

interface BaseDao<E> {

    fun insert(seed: E): Long

    fun getById(id: Long): E

    @Update
    fun update(vararg entity: E): Int

    @Delete
    fun delete(vararg entity: E): Int
}