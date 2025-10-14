package io.bitbot.bemusedbaboon.common.ui.viewmodel

import io.bitbot.bemusedbaboon.common.ui.view.Dialog


/**
 * The state of the view model.
 *
 * @property error a [Throwable]
 * @property dialog an instance of the [Dialog] interface.
 */
abstract class ViewModelState {
    abstract val error: Throwable?
    abstract val dialog: Dialog?
}
