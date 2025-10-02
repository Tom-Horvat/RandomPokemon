package io.bitbot.bemusedbaboon.data.entity.pokemon

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import io.bitbot.bemusedbaboon.data.entity.ability.Ability
import io.bitbot.bemusedbaboon.data.entity.cries.Cries
import io.bitbot.bemusedbaboon.data.entity.move.Move
import io.bitbot.bemusedbaboon.data.entity.species.Species
import io.bitbot.bemusedbaboon.data.entity.sprites.Sprites
import io.bitbot.bemusedbaboon.data.entity.stat.slot.StatSlot
import io.bitbot.bemusedbaboon.data.entity.type.slot.TypeSlot
import java.time.LocalDateTime

/**
 * A class representing a pokemon
 */
@Entity(tableName = "Pokemon")
data class Pokemon(
    @PrimaryKey
    override val pokemonId: Long,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    override val updatedAt: LocalDateTime,
    override val baseExperience: Int,
    override val height: Int,
    override val name: String,
    override val locationEncounters: String,
    override val weight: Int,
) : Props {

    data class Seed(
        override val pokemonId: Long,
        override val createdAt: LocalDateTime = LocalDateTime.now(),
        override val updatedAt: LocalDateTime = LocalDateTime.now(),
        override val baseExperience: Int,
        override val height: Int,
        override val name: String,
        override val locationEncounters: String,
        override val weight: Int,
    ) : Props

    data class Complete(
        @Embedded val pokemon: Pokemon,
        @Relation(
            parentColumn = "pokemonId",
            entityColumn = "abilityId",
            associateBy = Junction(PokemonAbility::class)
        )
        val abilities: List<Ability>,
        @Relation(
            parentColumn = "pokemonId",
            entityColumn = "pokemonId",
        )
        val cries: Cries,
        @Relation(
            parentColumn = "pokemonId",
            entityColumn = "moveId",
            associateBy = Junction(PokemonMove::class)
        )
        val moves: List<Move>,
        @Relation(
            parentColumn = "pokemonId",
            entityColumn = "pokemonId"
        )
        val species: Species,
        @Relation(
            parentColumn = "pokemonId",
            entityColumn = "pokemonId"
        )
        val sprites: Sprites,
        @Relation(
            entity = StatSlot::class,
            parentColumn = "pokemonId",
            entityColumn = "pokemonId"
        )
        val stats: List<StatSlot.Complete>,
        @Relation(
            entity = TypeSlot::class,
            parentColumn = "pokemonId",
            entityColumn = "pokemonId"
        )
        val types: List<TypeSlot.Complete>
    )
}