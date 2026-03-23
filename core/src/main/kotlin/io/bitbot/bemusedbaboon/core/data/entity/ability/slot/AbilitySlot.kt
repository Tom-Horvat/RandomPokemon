package io.bitbot.bemusedbaboon.core.data.entity.ability.slot

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import io.bitbot.bemusedbaboon.core.data.entity.ability.Ability
import java.time.LocalDateTime

@Entity(tableName = "AbilitySlot")
data class AbilitySlot(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val isHidden: Boolean,
    val abilityId: Long,
    val pokemonId: Long,
    val slot: Int,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)

data class Complete(
    @Embedded val entity: AbilitySlot,
    @Relation(
        parentColumn = "abilityId",
        entityColumn = "abilityId",
    )
    val ability: Ability,
)
