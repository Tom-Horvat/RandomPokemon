package io.bitbot.bemusedbaboon.core.data.entity.type.slot

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import io.bitbot.bemusedbaboon.core.data.entity.type.Type
import java.time.LocalDateTime

@Entity(tableName = "TypeSlot")
class TypeSlot(
    @PrimaryKey
    override val typeSlotId: Long,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    override val updatedAt: LocalDateTime,
    override val order: Int,
    override val typeId: Long,
    override val pokemonId: Long,
) : Props {

    /**
     * TypeSlot seed, used for creating database entries through the TypeSlotDto.
     **/
    data class Seed(
        override val typeSlotId: Long,
        override val order: Int,
        override val typeId: Long,
        override val pokemonId: Long,
        override val createdAt: LocalDateTime = LocalDateTime.now(),
        override val updatedAt: LocalDateTime = LocalDateTime.now(),
    ) : Props

    /**
     * A complete TypeSlot with all related properties.
     **/
    data class Complete(
        @Embedded val entity: TypeSlot,
        @Relation(
            parentColumn = "typeId",
            entityColumn = "typeId",
        )
        val type: Type,
    )
}