package io.bitbot.bemusedbaboon.core.data.entity.sprites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "Sprites")
class Sprites(
    @PrimaryKey
    val pokemonId: Long,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val backDefault: String?,
    val backFemale: String? = null,
    val backShiny: String? = null,
    val backShinyFemale: String? = null,
    val frontDefault: String,
    val frontFemale: String? = null,
    val fronShiny: String? = null,
    val frontShinyFemale: String? = null,
)
