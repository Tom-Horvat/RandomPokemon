package io.bitbot.bemusedbaboon.common.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
 * Intended to be used by ViewModels to simplify state and use case management.
 *
 * @property state The initial state of the ViewModel.
 *
 * @param state The observable state flow of the [ViewModelState] used by the Composables.
 * @param model A convenience property to obtain the current value of the [ViewModelState] used
 * for updating the state values.
 */
open class BaseViewModel<M>(state: ViewModelState) : ViewModel() {
    private val _state: MutableStateFlow<ViewModelState> =
        MutableStateFlow(state)
    val state: StateFlow<ViewModelState> = _state

    @Suppress("UNCHECKED_CAST")
    protected val model: M
        get() = this@BaseViewModel.state.value as M

    /**
     * Updates the base view model state.
     *
     * ```
     * model.copy(someParam = someValue).save()
     * ```
     */
    fun ViewModelState.save() {
        _state.update { this }
    }

    /**
     * Runs a list of use cases.
     *
     * ```
     * runScoped({ someUsecase(), anotherUsecase(someValue) })
     * ```
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
     *
     * ```
     * useCase.observe(
     *             onRunning = { m -> /* Do something with the message */ },
     *             onError = { e -> model.copy(error = e).save() }
     *         ) { data -> /* Do something with the data */ }
     * ```
     */
    protected inline fun <reified O> UseCase<O>.observe(
        crossinline onRunning: (String?) -> Unit = {},
        crossinline onError: (Throwable?) -> Unit = { e -> onUseCaseError(e) },
        crossinline onDone: suspend (O?) -> Unit = {}
    ) = viewModelScope.launch {
        this@observe.state.collect {
            it?.let {
                it.parse<O>(
                    onError = { e ->
                        onError(e)
                        //onUseCaseError(e)
                    },
                    onRunning = { m -> onRunning(m) },
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
