package io.bitbot.bemusedbaboon.core.data.entity.cries

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "Cry")
data class Cries(
    @PrimaryKey
    override val cryId: Long,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    override val updatedAt: LocalDateTime,
    override val latest: String,
    override val legacy: String,
    override val pokemonId: Long
) : Props {
    data class Seed(
        override val cryId: Long,
        override val createdAt: LocalDateTime = LocalDateTime.now(),
        override val updatedAt: LocalDateTime = LocalDateTime.now(),
        override val latest: String,
        override val legacy: String,
        override val pokemonId: Long,
    ) : Props
}