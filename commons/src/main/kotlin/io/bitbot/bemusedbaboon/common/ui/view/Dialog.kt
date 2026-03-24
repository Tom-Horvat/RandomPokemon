package io.bitbot.bemusedbaboon.common.ui.view

import androidx.compose.runtime.Immutable

@Immutable
interface Dialog {
    val title: String
    val content: String
}