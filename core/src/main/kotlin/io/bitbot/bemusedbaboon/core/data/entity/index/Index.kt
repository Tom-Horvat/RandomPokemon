package io.bitbot.bemusedbaboon.core.data.entity.index

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Index")
data class Index(
    @PrimaryKey
    val pokemonId: Long,
    val name: String,
)
