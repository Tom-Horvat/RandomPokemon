package io.bitbot.bemusedbaboon.common.ui.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable

interface BaseView<M> {

    val body: @Composable (M, PaddingValues) -> Unit

    @OptIn(ExperimentalMaterial3Api::class)
    val topBar: @Composable (M, TopAppBarScrollBehavior) -> Unit
        get() = { _, _ -> }
    val navDrawer: @Composable (M) -> Unit
        get() = {}
    val bottomSheet: @Composable (M) -> Unit
        get() = {}
    val bottomBar: @Composable (M) -> Unit
        get() = {}
}