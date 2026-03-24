package io.bitbot.bemusedbaboon.common.repository.index

import io.bitbot.bemusedbaboon.common.repository.util.networkBoundResource
import io.bitbot.bemusedbaboon.core.data.entity.index.Index
import io.bitbot.bemusedbaboon.core.data.repository.index.IndexRepo
import io.bitbot.bemusedbaboon.core.data.repository.index.InitializationProvider
import io.bitbot.bemusedbaboon.core.domain.dispatchers.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import timber.log.Timber

class IndexRepoImpl(
    private val local: IndexRepo.Local,
    private val remote: IndexRepo.Remote,
    private val dispatchers: DispatcherProvider
) : IndexRepo, InitializationProvider {

    private val _isInitialized = MutableStateFlow(false)
    override val isInitialized: StateFlow<Boolean> = _isInitialized.asStateFlow()

    override fun getPokemonCount() = flow {
        networkBoundResource<Int, Int>(
            query = { local.getPokemonCount() },
            fetch = { remote.getPokemonCount().last() },
            shouldFetch = { it!! <= 0 },
            saveFetchResult = { count -> cacheRemotePokemonCount(count) },
            mapDtoToDomain = { count -> count }
        ).collect { emit(it) }
    }

    override fun getPokemonIndex(count: Int) = flow {
        networkBoundResource<List<Index>, List<Index>>(
            query = { local.getPokemonIndex() },
            fetch = { remote.getPokemonIndex(count).last() },
            shouldFetch = { index ->
                index.isNullOrEmpty()
            },
            saveFetchResult = { index -> cacheRemotePokemonIndex(index) },
            mapDtoToDomain = { index -> index },
            onCachePresent = {
                _isInitialized.value = true
            }
        ).collect { result ->
            emit(result)
        }
    }

    private suspend fun cacheRemotePokemonCount(count: Int) {
        supervisorScope {
            launch(dispatchers.io) {
                try {
                    local.setPokemonCount(count)
                } catch (e: Exception) {
                    Timber.e(e, "Error caching pokemon count")
                }
            }
        }
    }

    private suspend fun cacheRemotePokemonIndex(index: List<Index>) {
        supervisorScope {
            launch(dispatchers.io) {
                try {
                    local.setPokemonIndex(index)
                    _isInitialized.value = true
                } catch (e: Exception) {
                    Timber.e(e, "Error caching pokemon index")
                }
            }
        }
    }
}