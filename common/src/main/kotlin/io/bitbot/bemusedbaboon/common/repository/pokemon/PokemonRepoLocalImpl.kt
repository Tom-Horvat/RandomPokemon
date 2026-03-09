package io.bitbot.bemusedbaboon.common.repository.pokemon

import io.bitbot.bemusedbaboon.core.data.dao.AbilitySlotDao
import io.bitbot.bemusedbaboon.core.data.dao.CriesDao
import io.bitbot.bemusedbaboon.core.data.dao.PokemonDao
import io.bitbot.bemusedbaboon.core.data.dao.SpritesDao
import io.bitbot.bemusedbaboon.core.data.entity.ability.slot.AbilitySlot
import io.bitbot.bemusedbaboon.core.data.entity.cries.Cries
import io.bitbot.bemusedbaboon.core.data.entity.pokemon.Pokemon
import io.bitbot.bemusedbaboon.core.data.entity.sprites.Sprites
import io.bitbot.bemusedbaboon.core.data.repository.pokemon.PokemonRepo
import io.bitbot.bemusedbaboon.core.domain.ResultStateFlowConverter

class PokemonRepoLocalImpl(
    private val pokemonDao: PokemonDao,
    private val criesDao: CriesDao,
    private val spritesDao: SpritesDao,
    private val abilitySlotDao: AbilitySlotDao,
) : ResultStateFlowConverter(), PokemonRepo.Local {
    override suspend fun savePokemon(seed: Pokemon) = toResultStateFlow {
        pokemonDao.insert(seed = seed)
    }

    override suspend fun saveCries(seed: Cries) = toResultStateFlow {
        criesDao.insert(seed)
    }

    override suspend fun saveSprites(seed: Sprites) = toResultStateFlow {
        spritesDao.insert(seed)
    }

    override suspend fun saveAbilities(seeds: List<AbilitySlot>) = toResultStateFlow {
        if (seeds.isNotEmpty()) abilitySlotDao.insertAll(seeds)
        else emptyList()
    }

    override suspend fun getPokemonById(id: Long) = toResultStateFlow {
        pokemonDao.getCompleteById(id)
    }
}