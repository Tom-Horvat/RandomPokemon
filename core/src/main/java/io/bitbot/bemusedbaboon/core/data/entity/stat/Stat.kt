package io.bitbot.bemusedbaboon.core.data.entity.stat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "Stat")
class Stat(
    @PrimaryKey
    override val statId: Long,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    override val updatedAt: LocalDateTime,
    override val isBattleOnly: Boolean,
    override val name: String,
) : Props {

    data class Seed(
        override val statId: Long,
        override val createdAt: LocalDateTime = LocalDateTime.now(),
        override val updatedAt: LocalDateTime = LocalDateTime.now(),
        override val isBattleOnly: Boolean,
        override val name: String,
    ) : Props
}