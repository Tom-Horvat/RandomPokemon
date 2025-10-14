package io.bitbot.bemusedbaboon.core.data.entity.index

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Index")
data class Index(
    @PrimaryKey
    override val pokemonId: Long,
    override val name: String,
) : Props
