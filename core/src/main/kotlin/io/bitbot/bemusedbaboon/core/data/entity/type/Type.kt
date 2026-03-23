package io.bitbot.bemusedbaboon.core.data.entity.type

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "Type")
class Type(
    @PrimaryKey
    val typeId: Long,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val updatedAt: LocalDateTime,

    val name: String,
)