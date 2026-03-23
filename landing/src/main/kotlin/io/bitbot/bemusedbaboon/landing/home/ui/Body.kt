package io.bitbot.bemusedbaboon.landing.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun Body(
    padding: PaddingValues,
    name: String,
    spriteUrl: String? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Card {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.weight(1f),
                    model = spriteUrl,
                    contentDescription = null
                )
                Column(modifier = Modifier.weight(2f)) {
                    Text(text = name, style = MaterialTheme.typography.titleLarge)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BodyPreview() {
    Body(
        padding = PaddingValues(16.dp),
        name = "Bulbasaur",
        spriteUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/1.png"
    )
}