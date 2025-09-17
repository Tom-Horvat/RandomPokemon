package io.bitbot.bemusedbaboon.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.bitbot.bemusedbaboon.domain.Stat

/**
 * Shows the name, front and back images and the stats of the pokemon
 */
@Composable
fun Pokedex(
    name: String,
    frontImage: String,
    backImage: String,
    stats: List<Stat>
) {
    val showLoading = remember {
        mutableStateOf(true)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            PokemonName(
                name = name,
                modifier = Modifier
                    .padding(
                        horizontal = 12.dp,
                        vertical = 4.dp,
                    )
            )
            PokemonImage(
                frontImage = frontImage,
                backImage = backImage,
                onImageLoaded = { showLoading.value = !it },
                showLoading = showLoading.value,
            )
            PokemonStats(stats = stats)
        }
    }
}