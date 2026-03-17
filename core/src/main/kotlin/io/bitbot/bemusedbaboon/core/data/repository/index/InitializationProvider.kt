package io.bitbot.bemusedbaboon.core.data.repository.index

import kotlinx.coroutines.flow.StateFlow

interface InitializationProvider {
    val isInitialized: StateFlow<Boolean>
}
