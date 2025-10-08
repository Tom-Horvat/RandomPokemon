package io.bitbot.bemusedbaboon.data.entity.index

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "Index")
data class Index(
    @PrimaryKey
    override val pokemonId: Long,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    override val updatedAt: LocalDateTime,
    override val name: String,
) : Props {
    data class Seed(
        override val pokemonId: Long,
        override val name: String,
        override val createdAt: LocalDateTime = LocalDateTime.now(),
        override val updatedAt: LocalDateTime = LocalDateTime.now(),
    ) : Props
}