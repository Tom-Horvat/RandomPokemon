package io.bitbot.bemusedbaboon.common.repository.pokemon

import io.bitbot.bemusedbaboon.common.api.data.PokemonDto
import io.bitbot.bemusedbaboon.common.api.data.toDomainModel
import io.bitbot.bemusedbaboon.common.repository.util.networkBoundResource
import io.bitbot.bemusedbaboon.core.data.repository.pokemon.PokemonRepo
import io.bitbot.bemusedbaboon.core.domain.dispatchers.DispatcherProvider
import io.bitbot.bemusedbaboon.core.domain.model.PokemonDetails
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import timber.log.Timber

class PokemonRepoImpl(
    private val remote: PokemonRepo.Remote,
    private val local: PokemonRepo.Local,
    private val dispatchers: DispatcherProvider
) : PokemonRepo {
    override fun getPokemonById(id: Long) = networkBoundResource<PokemonDetails, PokemonDto>(
        query = { local.getPokemonById(id) },
        fetch = { remote.getPokemonById(id).last() },
        saveFetchResult = { dto -> cacheRemotePokemon(dto.toDomainModel()) },
        mapDtoToDomain = { dto -> dto.toDomainModel() }
    )

    private suspend fun cacheRemotePokemon(details: PokemonDetails) {
        supervisorScope {
            val saveOperations: List<Pair<String, suspend () -> Unit>> = listOf(
                "pokemon" to { local.savePokemon(details.pokemon) },
                "cries" to { local.saveCries(details.cries) },
                "sprites" to { local.saveSprites(details.sprites) },
                "abilities" to { local.saveAbilities(details.abilities) }
            )

            saveOperations.forEach { (name, action) ->
                launch(dispatchers.io) {
                    try {
                        action()
                    } catch (e: Exception) {
                        Timber.e(e, "Error caching $name")
                    }
                }
            }
        }
    }
}