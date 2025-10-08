package io.bitbot.bemusedbaboon.landing.home.view

import androidx.compose.runtime.Composable
import io.bitbot.bemusedbaboon.landing.home.HomeState
import io.bitbot.bemusedbaboon.landing.home.HomeViewModel
import io.bitbot.bemusedbaboon.common.view.BaseView

class HomeView : BaseView<HomeState, HomeViewModel> {

    override val body: @Composable (HomeState, HomeViewModel) -> Unit = { state, viewModel ->
    }
}