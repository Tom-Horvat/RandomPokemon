package io.bitbot.bemusedbaboon.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bitbot.bemusedbaboon.common.view.BaseView
import io.bitbot.bemusedbaboon.core.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Inherited by all ViewModel classes. Contains shared functionality for ViewModels.
 *
 * @constructor takes in a model of the extra data for the ViewModel and the view associated with
 * the ViewModel
 *
 * @property view the [BaseView] associated with this ViewModel.
 * @property state the observable [StateFlow] of this ViewModels' data model.
 * @property model the current state of this ViewModels' data model to be used as reference when
 * updating.
 */
open class BaseViewModel<M, VM>(
    model: ViewModelState,
    view: () -> BaseView<M, VM>
) : ViewModel() {
    private val _view: MutableStateFlow<BaseView<M, VM>> = MutableStateFlow(view())
    private val _state: MutableStateFlow<ViewModelState> =
        MutableStateFlow(model)
    val view: StateFlow<BaseView<M, VM>> = _view
    val state: StateFlow<ViewModelState> = _state

    @Suppress("UNCHECKED_CAST")
    protected val model: M
        get() = state.value as M

    /**
     * Updates the base view model state.
     */
    fun ViewModelState.save() {
        _state.update { this }
    }

    /**
     * Runs a list of use cases.
     */
    protected fun runScoped(
        vararg useCases: suspend () -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.Default,
    ) {
        useCases.forEach {
            viewModelScope.launch(dispatcher) { it() }
        }
    }

    /**
     * Observes the given [UseCase] and executes the given lambda.
     */
    protected inline fun <reified O> UseCase<O>.observe(
        crossinline onRunning: () -> Unit = {},
        crossinline onError: (Throwable?) -> Unit = { error ->
            onUseCaseError(error)
        },
        crossinline onDone: suspend (O?) -> Unit = {}
    ) = viewModelScope.launch {
        this@observe.state.collect {
            it?.let {
                it.parse<Any?, O>(
                    onError = { e -> onError(e) },
                    onRunning = { onRunning() },
                ) { result -> onDone(result) }
            }
        }
    }


    /**
     * Observes a [Flow] and executes the given lambda.
     */
    @OptIn(FlowPreview::class)
    protected fun <T> Flow<T>.observe(
        debounceMillis: Long = 0,
        callback: suspend (T) -> Unit = {}
    ) = viewModelScope.launch {
        this@observe
            .debounce(timeoutMillis = debounceMillis)
            .catch { e -> onUseCaseError(e) }
            .collect { it?.let { callback(it) } }
    }

    protected fun onUseCaseError(error: Throwable?) {
        Timber.e("ERROR: %s", error?.message)
    }
}