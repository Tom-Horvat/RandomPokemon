package io.bitbot.bemusedbaboon.core.data.entity.species

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "Species")
class Species(
    @PrimaryKey(autoGenerate = true)
    override val speciesId: Int,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    override val updatedAt: LocalDateTime,
    override val name: String,
    override val pokemonId: Long
) : Props {

    data class Seed(
        override val speciesId: Int,
        override val createdAt: LocalDateTime = LocalDateTime.now(),
        override val updatedAt: LocalDateTime = LocalDateTime.now(),
        override val name: String,
        override val pokemonId: Long,
    ) : Props
}