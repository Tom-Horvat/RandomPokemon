package io.bitbot.bemusedbaboon.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import io.bitbot.bemusedbaboon.data.entity.pokemon.Pokemon

@Dao
interface PokemonDao : BaseDao<Pokemon, Pokemon.Seed> {

    @Insert(entity = Pokemon::class)
    override fun insertSeed(seed: Pokemon.Seed): Long

    @Query("SELECT * FROM Pokemon WHERE pokemonId IS :id")
    override fun getById(id: Long): Pokemon

    @Transaction
    @Query("SELECT * FROM Pokemon WHERE pokemonId IS :id")
    fun getCompleteById(id: Long): Pokemon.Complete

    @Transaction
    @Query("SELECT * FROM Pokemon")
    fun getCompletePokemon(): List<Pokemon.Complete>
}