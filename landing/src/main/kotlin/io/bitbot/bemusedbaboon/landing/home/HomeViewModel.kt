package io.bitbot.bemusedbaboon.landing.home

import io.bitbot.bemusedbaboon.common.ui.viewmodel.BaseViewModel
import io.bitbot.bemusedbaboon.core.domain.usecase.index.GetPokemon
import io.bitbot.bemusedbaboon.core.domain.usecase.index.GetPokemonIndex
import io.bitbot.bemusedbaboon.core.domain.usecase.index.GetRandomIndex
import io.bitbot.bemusedbaboon.landing.home.state.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber

class HomeViewModel(
    getPokemonIndex: GetPokemonIndex,
    private val getRandomIndex: GetRandomIndex,
    private val getPokemon: GetPokemon,
) : BaseViewModel<HomeState>(state = HomeState()) {
    private val randomIndex: MutableStateFlow<Long?> = MutableStateFlow(null)

    init {
        getPokemonIndex.observe { if (!it.isNullOrEmpty()) runScoped({ getRandomIndex() }) }

        getRandomIndex.observe(onError = { model.copy(error = it).save() }) { index ->
            index?.let { randomIndex.value = it } ?: run { Timber.i("No random index fetched") }
        }

        getPokemon.observe(
            onRunning = { m -> Timber.i(m) },
            onError = {
                model.copy(error = it).save()
                onUseCaseError(it)
            }
        ) { pokemon ->
            Timber.i("Pokemon: $pokemon")
            pokemon?.let {
                model.copy(
                    name = pokemon.pokemon.name,
                    spriteUrl = pokemon.sprites.frontDefault
                ).save()
            }
        }

        randomIndex.observe { it?.let { runScoped({ getPokemon(it) }) } }
    }
}