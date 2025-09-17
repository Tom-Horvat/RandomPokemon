package io.bitbot.bemusedbaboon.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.bitbot.bemusedbaboon.R
import io.bitbot.bemusedbaboon.domain.Pokemon
import io.bitbot.bemusedbaboon.domain.Stat
import io.bitbot.bemusedbaboon.ui.theme.RandomPokemonTheme
import io.bitbot.bemusedbaboon.ui.viewmodel.PokemonViewModel

/**
 * Holds the root layout of the application UI.
 *
 * @param pokemonViewModel a view model used with the UI
 */
@Composable
fun MainScreen(
    pokemonViewModel: PokemonViewModel,
) {
    val pokemon: Pokemon? by pokemonViewModel.pokemon.collectAsState()

    RandomPokemonTheme {
        RootLayout(
            name = pokemon?.name ?: stringResource(id = R.string.loading_lbl),
            frontImage = pokemon?.frontImageUrl ?: stringResource(id = R.string.empty_string),
            backImage = pokemon?.backImageUrl ?: stringResource(id = R.string.empty_string),
            stats = pokemon?.stats ?: emptyList(),
            moves = pokemon?.moves ?: emptyList(),
            onGetPokemonClicked = { pokemonViewModel.onGetPokemonClicked() }
        )
    }
}

/**
 * Holds the Pokedex and Moves list
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootLayout(
    name: String,
    frontImage: String,
    backImage: String,
    stats: List<Stat>,
    moves: List<String>,
    onGetPokemonClicked: (() -> Unit),
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onGetPokemonClicked() }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_refresh_24),
                    contentDescription = null,
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Menu, stringResource(id = R.string.empty_string))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 8.dp,
                        top = 8.dp,
                        end = 8.dp,
                        bottom = 0.dp,
                    )
            ) {
                Pokedex(
                    name = name,
                    frontImage = frontImage,
                    backImage = backImage,
                    stats = stats
                )
                PokemonMoves(moves = moves)
            }
        }
    }
}