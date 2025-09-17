package io.bitbot.bemusedbaboon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.Observer
import io.bitbot.bemusedbaboon.domain.PokeIndex
import io.bitbot.bemusedbaboon.ui.view.MainScreen
import io.bitbot.bemusedbaboon.ui.viewmodel.PokemonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * The main app activity.
 */
class MainActivity : ComponentActivity() {

    private val pokemonViewModel: PokemonViewModel by viewModel()

    private val isPokeIndexCachedObserver = Observer<Boolean> {
        if (!it) pokemonViewModel.onActivityResumed(false)
    }

    private val pokeIndexObserver = Observer<PokeIndex?> {
        it?.run { pokemonViewModel.onGetPokemonClicked() }
    }

    private val failedFetchPokemonObserver = Observer<Int?> {
        it?.run { pokemonViewModel.onGetPokemonClicked(false) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setObservers()

        setContent { MainScreen(pokemonViewModel = pokemonViewModel) }


    }

    private fun setObservers() {
        pokemonViewModel.isPokeIndexCached.observe(this, isPokeIndexCachedObserver)
        pokemonViewModel.pokeIndex.observe(this, pokeIndexObserver)
        pokemonViewModel.failedFetchPokemonId.observe(this, failedFetchPokemonObserver)
    }

    override fun onResume() {
        super.onResume()

        pokemonViewModel.onActivityResumed()
    }
}