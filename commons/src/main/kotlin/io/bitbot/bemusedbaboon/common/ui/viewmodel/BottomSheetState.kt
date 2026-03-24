package io.bitbot.bemusedbaboon.common.ui.viewmodel

abstract class BottomSheetState<B>() : ViewModelState() {
    abstract val bottomSheetVisible: Boolean
    abstract val bottomSheetView: B
    abstract fun showSheet(view: B = this.bottomSheetView): ViewModelState
    abstract fun hideSheet(): ViewModelState
}