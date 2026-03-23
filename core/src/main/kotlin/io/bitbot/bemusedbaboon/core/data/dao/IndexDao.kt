package io.bitbot.bemusedbaboon.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.bitbot.bemusedbaboon.core.data.entity.index.Index

@Dao
interface IndexDao : BaseDao<Index> {

    @Insert(entity = Index::class)
    override fun insert(seed: Index): Long

    @Insert(entity = Index::class)
    fun insertAll(items: List<Index>): List<Long>

    @Query("SELECT * FROM `Index`")
    fun getAll(): List<Index>

    @Query("SELECT * FROM `Index` WHERE pokemonId IS :id")
    override fun getById(id: Long): Index

    @Query("SELECT COUNT() FROM `Index`")
    fun getIndexCount(): Int

    @Query("SELECT pokemonId FROM `Index` ORDER BY RANDOM() LIMIT 1")
    fun getRandomIndex(): Long
}