package io.bitbot.bemusedbaboon.landing.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import io.bitbot.bemusedbaboon.common.ui.view.RootView
import io.bitbot.bemusedbaboon.landing.home.state.HomeState
import io.bitbot.bemusedbaboon.landing.home.ui.HomeView
import org.koin.androidx.compose.koinViewModel

class HomeScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = koinViewModel()
        val view = HomeView()
        val state by viewModel.state.collectAsState()

        RootView() {
            view.body(state as HomeState, viewModel)
        }
    }
}