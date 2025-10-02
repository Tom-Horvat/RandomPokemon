package io.bitbot.bemusedbaboon.data.entity.sprites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "Sprites")
class Sprites(
    @PrimaryKey
    override val pokemonId: Long,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    override val updatedAt: LocalDateTime,
    override val backDefault: String,
    override val backFemale: String?,
    override val backShiny: String,
    override val backShinyFemale: String?,
    override val frontDefault: String,
    override val frontFemale: String?,
    override val fronShiny: String,
    override val frontShinyFemale: String?,
) : Props {

    data class Seed(
        override val pokemonId: Long,
        override val backDefault: String,
        override val backFemale: String?,
        override val backShiny: String,
        override val backShinyFemale: String?,
        override val frontDefault: String,
        override val frontFemale: String?,
        override val fronShiny: String,
        override val frontShinyFemale: String?,
        override val createdAt: LocalDateTime = LocalDateTime.now(),
        override val updatedAt: LocalDateTime = LocalDateTime.now(),
    ) : Props
}