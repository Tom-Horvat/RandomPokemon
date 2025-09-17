package io.bitbot.bemusedbaboon.ui.view

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Displays the pokemon name
 */
@Composable
fun PokemonName(
    name: String,
    modifier: Modifier
) {
    Text(
        text = name,
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge
    )
}
