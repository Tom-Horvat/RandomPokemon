package io.bitbot.bemusedbaboon.core.data.entity.species

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "Species")
class Species(
    @PrimaryKey(autoGenerate = true)
    val speciesId: Int,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val updatedAt: LocalDateTime,
    val name: String,
    val pokemonId: Long
)