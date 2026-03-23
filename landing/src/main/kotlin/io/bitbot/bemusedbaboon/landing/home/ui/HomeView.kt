package io.bitbot.bemusedbaboon.landing.home.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import io.bitbot.bemusedbaboon.common.ui.view.BaseView
import io.bitbot.bemusedbaboon.landing.home.state.HomeState

class HomeView : BaseView<HomeState> {

    override val body: @Composable (HomeState, PaddingValues) -> Unit = { state, padding ->
        Body(
            padding = padding,
            name = state.name,
            spriteUrl = state.spriteUrl
        )
    }
}