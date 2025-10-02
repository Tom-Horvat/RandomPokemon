package io.bitbot.bemusedbaboon.data.entity.move

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "Move")
data class Move(
    @PrimaryKey
    override val moveId: Long,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    override val updatedAt: LocalDateTime,
    override val name: String,
) : Props {
    data class Seed(
        override val moveId: Long,
        override val createdAt: LocalDateTime = LocalDateTime.now(),
        override val updatedAt: LocalDateTime = LocalDateTime.now(),
        override val name: String,
    ) : Props
}