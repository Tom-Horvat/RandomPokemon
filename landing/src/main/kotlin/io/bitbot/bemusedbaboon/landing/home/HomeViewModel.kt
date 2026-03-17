package io.bitbot.bemusedbaboon.landing.home

import io.bitbot.bemusedbaboon.common.ui.viewmodel.BaseViewModel
import io.bitbot.bemusedbaboon.core.domain.usecase.index.GetPokemonCount
import io.bitbot.bemusedbaboon.core.domain.usecase.index.GetPokemonIndex
import io.bitbot.bemusedbaboon.core.domain.usecase.index.GetRandomIndex
import io.bitbot.bemusedbaboon.core.domain.usecase.pokemon.GetPokemon
import io.bitbot.bemusedbaboon.landing.home.event.HomeEvent
import io.bitbot.bemusedbaboon.landing.home.state.HomeState

class HomeViewModel(
    getPokemonIndex: GetPokemonIndex,
    getPokemonCount: GetPokemonCount,
    private val getRandomIndex: GetRandomIndex,
    private val getPokemon: GetPokemon,
) : BaseViewModel<HomeState>(initialState = HomeState()) {

    init {
        getPokemonCount.collectUseCase {
            it?.let { runScoped({ getPokemonIndex(it) }) }
        }
        getPokemonIndex.collectUseCase(
            onError = { error ->
                updateState { it.copy(error = error) }
                onUseCaseError(error)
            }
        ) { runScoped({ getRandomIndex() }) }

        getRandomIndex.collectUseCase(
            onError = { error -> updateState { it.copy(error = error) } }
        ) { index ->
            index?.let { if (it > 0) runScoped({ getPokemon(index) }) }
        }

        getPokemon.collectUseCase(
            onError = { error ->
                updateState { it.copy(error = error) }
                onUseCaseError(error)
            }
        ) { pokemon ->
            pokemon?.let {
                updateState {
                    it.copy(
                        name = pokemon.pokemon.name,
                        spriteUrl = pokemon.sprites.frontDefault
                    )
                }

            }
        }
    }


    fun onEvent(event: HomeEvent) {
        when (event) {
            else -> {}
        }
    }

}