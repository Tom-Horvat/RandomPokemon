package io.bitbot.bemusedbaboon.core.domain.usecase.index

import io.bitbot.bemusedbaboon.core.data.entity.index.Index
import io.bitbot.bemusedbaboon.core.data.repository.index.IndexRepo
import io.bitbot.bemusedbaboon.core.domain.usecase.UseCase

class GetPokemonIndex(
    private val local: IndexRepo.Local,
    private val remote: IndexRepo
) : UseCase<List<Index>>() {
    suspend operator fun invoke(pokemonCount: Int) {
        local.getPokemonIndex().collect { result ->
            result.parse<List<Index>>(
                onRunning = { running("Getting index...") },
                onError = { e -> e.update() }
            ) { index ->
                if (index.isNullOrEmpty()) fetchIndexFromRemote(pokemonCount)
                else index.update()
            }
        }
    }

    private suspend fun fetchIndexFromRemote(count: Int) {
        remote.getPokemonIndex(count = count).collect { result ->
            result.parse<List<Index>>(
                onRunning = { running("Fetching from server...") },
                onError = { e -> e.update() }
            ) { index ->
                index?.let {
                    saveIndexToLocalStorage(it)
                    it.update()
                } ?: run {
                    Throwable("Unable to fetch the index from the server. Try again later.").update()
                    listOf<Index>().update()
                }
            }
        }
    }

    private suspend fun saveIndexToLocalStorage(index: List<Index>) {
        local.setPokemonIndex(index).collect { result ->
            result.parse<List<Long>>(
                onError = { e -> e.update() }
            ) { }
        }
    }
}