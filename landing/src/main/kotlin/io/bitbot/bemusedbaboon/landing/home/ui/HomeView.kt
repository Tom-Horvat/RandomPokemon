package io.bitbot.bemusedbaboon.landing.home.ui

import androidx.compose.runtime.Composable
import io.bitbot.bemusedbaboon.common.ui.view.BaseView
import io.bitbot.bemusedbaboon.landing.home.HomeViewModel
import io.bitbot.bemusedbaboon.landing.home.state.HomeState

class HomeView : BaseView<HomeState, HomeViewModel> {

    override val body: @Composable (HomeState, HomeViewModel) -> Unit = { state, viewModel ->
        Body(
            name = state.name,
            spriteUrl = state.spriteUrl
        )
    }
}