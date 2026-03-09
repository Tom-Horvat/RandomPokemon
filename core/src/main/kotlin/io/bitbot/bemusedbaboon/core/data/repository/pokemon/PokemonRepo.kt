package io.bitbot.bemusedbaboon.core.data.repository.pokemon

import io.bitbot.bemusedbaboon.core.data.entity.ability.slot.AbilitySlot
import io.bitbot.bemusedbaboon.core.data.entity.cries.Cries
import io.bitbot.bemusedbaboon.core.data.entity.pokemon.Pokemon
import io.bitbot.bemusedbaboon.core.data.entity.sprites.Sprites
import io.bitbot.bemusedbaboon.core.domain.ResultState
import kotlinx.coroutines.flow.Flow

interface PokemonRepo {
    suspend fun getPokemonById(id: Long): Flow<ResultState>

    interface Local : PokemonRepo {
        suspend fun savePokemon(seed: Pokemon): Flow<ResultState>
        suspend fun saveCries(seed: Cries): Flow<ResultState>
        suspend fun saveSprites(seed: Sprites): Flow<ResultState>
        suspend fun saveAbilities(seeds: List<AbilitySlot>): Flow<ResultState>
    }
}