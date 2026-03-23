package io.bitbot.bemusedbaboon.core.data.entity.stat.slot

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import io.bitbot.bemusedbaboon.core.data.entity.stat.Stat
import java.time.LocalDateTime

/**
 * PersonalStat database entity.
 **/
@Entity(tableName = "StatSlot")
class StatSlot(
    @PrimaryKey
    val statSlotId: Long,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val updatedAt: LocalDateTime,
    val baseStat: Int,
    val effort: Int,
    val statId: Long,
    val pokemonId: Long
) {
    data class Complete(
        @Embedded val statSlot: StatSlot,
        @Relation(
            parentColumn = "statId",
            entityColumn = "statId"
        )
        val stat: Stat
    )
}