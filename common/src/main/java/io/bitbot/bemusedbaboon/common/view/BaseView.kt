package io.bitbot.bemusedbaboon.common.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable

interface BaseView<M, VM> {

    val body: @Composable (M, VM) -> Unit

    @OptIn(ExperimentalMaterial3Api::class)
    val topBar: @Composable (M, VM, TopAppBarScrollBehavior) -> Unit
        get() = { _, _, _ -> }
    val navDrawer: @Composable (M, VM) -> Unit
        get() = { _, _ -> }
    val bottomSheet: @Composable (M, VM) -> Unit
        get() = { _, _ -> }
    val bottomBar: @Composable (M, VM) -> Unit
        get() = { _, _ -> }
}