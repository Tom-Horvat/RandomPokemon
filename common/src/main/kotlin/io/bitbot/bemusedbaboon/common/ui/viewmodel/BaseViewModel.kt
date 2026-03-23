package io.bitbot.bemusedbaboon.common.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bitbot.bemusedbaboon.core.domain.dispatchers.DispatcherProvider
import io.bitbot.bemusedbaboon.core.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber

/**
 * Intended to be used by ViewModels to simplify state and use case management.
 *
 * @property initialState The initial state of the ViewModel.
 *
 * @param state The observable state flow of the [ViewModelState] used by the Composables.
 */
open class BaseViewModel<M : ViewModelState>(initialState: M) : ViewModel(), KoinComponent {
    private val dispatchers: DispatcherProvider by inject()
    private val _state: MutableStateFlow<M> = MutableStateFlow(initialState)
    val state: StateFlow<M> = _state.asStateFlow()

    /**
     * Updates the base view model state.
     *
     * ```
     * model.copy(someParam = someValue).save()
     * ```
     */
    protected fun updateState(transform: (M) -> M) {
        _state.update(transform)
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
        dispatcher: CoroutineDispatcher = dispatchers.default,
    ) {
        useCases.forEach {
            viewModelScope.launch(dispatcher) { it() }
        }
    }

    /**
     * Collects the given [UseCase] and executes the given lambda.
     *
     * ```
     * useCase.collectUseCase(
     *             onRunning = { m -> /* Do something with the message */ },
     *             onError = { e -> model.copy(error = e).save() }
     *         ) { data -> /* Do something with the data */ }
     * ```
     */
    protected inline fun <reified O> UseCase<O>.collectUseCase(
        crossinline onRunning: (String?) -> Unit = {},
        crossinline onError: (Throwable?) -> Unit = { e -> onUseCaseError(e) },
        crossinline onDone: suspend (O?) -> Unit = {}
    ) = viewModelScope.launch {
        this@collectUseCase.state.collect {
            it?.let {
                it.parse<O>(
                    onError = { e ->
                        onError(e)
                    },
                    onRunning = { m -> onRunning(m) },
                ) { result -> onDone(result) }
            }
        }
    }


    /**
     * Collects a [Flow] and executes the given lambda.
     */
    @OptIn(FlowPreview::class)
    protected fun <T> Flow<T>.collectFlow(
        debounceMillis: Long = 0,
        callback: suspend (T) -> Unit = {}
    ) = viewModelScope.launch {
        this@collectFlow
            .debounce(timeoutMillis = debounceMillis)
            .catch { e -> onUseCaseError(e) }
            .collect { it?.let { callback(it) } }
    }

    protected fun onUseCaseError(error: Throwable?) {
        Timber.e("ERROR: %s", error?.message)
    }
}
