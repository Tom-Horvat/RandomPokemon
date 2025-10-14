package io.bitbot.bemusedbaboon.common.ui.view

import androidx.compose.runtime.Composable

interface Dialog {
    val title: @Composable (() -> Unit)?
        get() = null
    val onDismiss: () -> Unit
    val onOk: (() -> Unit)?
    val content: @Composable () -> Unit
}