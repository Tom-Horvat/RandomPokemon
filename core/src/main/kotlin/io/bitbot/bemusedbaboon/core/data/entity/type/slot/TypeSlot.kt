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
    val typeSlotId: Long,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val updatedAt: LocalDateTime,
    val order: Int,
    val typeId: Long,
    val pokemonId: Long,
) {

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