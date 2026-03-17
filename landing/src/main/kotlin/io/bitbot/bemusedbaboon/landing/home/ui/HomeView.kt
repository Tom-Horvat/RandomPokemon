package io.bitbot.bemusedbaboon.landing.home.ui

import androidx.compose.runtime.Composable
import io.bitbot.bemusedbaboon.common.ui.view.BaseView
import io.bitbot.bemusedbaboon.landing.home.state.HomeState

class HomeView : BaseView<HomeState> {

    override val body: @Composable (HomeState) -> Unit = { state ->
        Body(
            name = state.name,
            spriteUrl = state.spriteUrl
        )
    }
}