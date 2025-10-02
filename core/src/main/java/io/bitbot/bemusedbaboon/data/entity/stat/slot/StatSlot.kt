package io.bitbot.bemusedbaboon.data.entity.stat.slot

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import io.bitbot.bemusedbaboon.data.entity.stat.Stat
import java.time.LocalDateTime

/**
 * PersonalStat database entity.
 **/
@Entity(tableName = "StatSlot")
class StatSlot(
    @PrimaryKey
    override val statSlotId: Long,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    override val updatedAt: LocalDateTime,
    override val baseStat: Int,
    override val effort: Int,
    override val statId: Long,
    override val pokemonId: Long
) : Props {

    /**
     * PersonalStat seed, used for creating database entries through the PersonalStatDto.
     **/
    data class Seed(
        override val statSlotId: Long,
        override val createdAt: LocalDateTime = LocalDateTime.now(),
        override val updatedAt: LocalDateTime = LocalDateTime.now(),
        override val baseStat: Int,
        override val effort: Int,
        override val statId: Long,
        override val pokemonId: Long,
    ) : Props

    data class Complete(
        @Embedded val statSlot: StatSlot,
        @Relation(
            parentColumn = "statId",
            entityColumn = "statId"
        )
        val stat: Stat
    )
}