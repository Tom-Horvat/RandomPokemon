package io.bitbot.bemusedbaboon.landing.home


import io.bitbot.bemusedbaboon.common.view.Dialog
import io.bitbot.bemusedbaboon.common.viewmodel.ViewModelState

data class HomeState(
    val data: Any? = null,
    override val error: Throwable? = null,
    override val dialog: Dialog? = null
) : ViewModelState()