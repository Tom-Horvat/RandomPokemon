package io.bitbot.bemusedbaboon.core.data.entity.cries

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "Cries")
data class Cries(
    @PrimaryKey
    val pokemonId: Long,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val latest: String,
    val legacy: String?,
)