package io.bitbot.bemusedbaboon.landing.home.state

import io.bitbot.bemusedbaboon.common.ui.view.Dialog
import io.bitbot.bemusedbaboon.common.ui.viewmodel.ViewModelState

data class HomeState(
    val name: String = "N/A",
    val spriteUrl: String? = null,
    override val error: Throwable? = null,
    override val dialog: Dialog? = null
) : ViewModelState()