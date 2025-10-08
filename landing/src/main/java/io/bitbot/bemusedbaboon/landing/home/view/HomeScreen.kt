package io.bitbot.bemusedbaboon.landing.home.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import io.bitbot.bemusedbaboon.common.view.RootView
import io.bitbot.bemusedbaboon.landing.home.HomeState
import io.bitbot.bemusedbaboon.landing.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

class HomeScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = koinViewModel()
        val view by viewModel.view.collectAsState()
        val model by viewModel.state.collectAsState()

        // Wrap the root view in a theme
        RootView() {
            view.body(model as HomeState, viewModel)
        }
    }
}