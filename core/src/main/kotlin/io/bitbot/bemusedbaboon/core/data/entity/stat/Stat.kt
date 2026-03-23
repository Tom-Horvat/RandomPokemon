package io.bitbot.bemusedbaboon.core.data.entity.stat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "Stat")
class Stat(
    @PrimaryKey
    val statId: Long,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val updatedAt: LocalDateTime,
    val isBattleOnly: Boolean,
    val name: String,
)