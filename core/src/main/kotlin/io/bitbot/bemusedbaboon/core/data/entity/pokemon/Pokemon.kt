package io.bitbot.bemusedbaboon.core.data.entity.pokemon

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import io.bitbot.bemusedbaboon.core.data.entity.ability.slot.AbilitySlot
import io.bitbot.bemusedbaboon.core.data.entity.cries.Cries
import io.bitbot.bemusedbaboon.core.data.entity.sprites.Sprites
import java.time.LocalDateTime

/**
 * A class representing a pokemon
 */
@Entity(tableName = "Pokemon")
data class Pokemon(
    @PrimaryKey
    val pokemonId: Long,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val baseExperience: Int,
    val height: Int,
    val name: String,
    val locationEncounters: String,
    val weight: Int,
) {

    data class Complete(
        @Embedded val pokemon: Pokemon,
        @Relation(
            parentColumn = "pokemonId",
            entityColumn = "pokemonId",
        )
        val cries: Cries,
        @Relation(
            parentColumn = "pokemonId",
            entityColumn = "pokemonId",
        )
        val abilitySlots: List<AbilitySlot>,
//        @Relation(
//            parentColumn = "pokemonId",
//            entityColumn = "moveId",
//            associateBy = Junction(PokemonMove::class)
//        )
//        val moves: List<Move>,
//        @Relation(
//            parentColumn = "pokemonId",
//            entityColumn = "pokemonId"
//        )
//        val species: Species,
        @Relation(
            parentColumn = "pokemonId",
            entityColumn = "pokemonId"
        )
        val sprites: Sprites,
//        @Relation(
//            entity = StatSlot::class,
//            parentColumn = "pokemonId",
//            entityColumn = "pokemonId"
//        )
//        val stats: List<StatSlot.Complete>,
//        @Relation(
//            entity = TypeSlot::class,
//            parentColumn = "pokemonId",
//            entityColumn = "pokemonId"
//        )
//        val types: List<TypeSlot.Complete>
    )
}