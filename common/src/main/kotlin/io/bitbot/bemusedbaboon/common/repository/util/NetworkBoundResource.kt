package io.bitbot.bemusedbaboon.common.repository.util

import io.bitbot.bemusedbaboon.core.domain.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import timber.log.Timber

/**
 * A generic function that provides a resource from the cache, falling back to a network fetch
 * if the cache is empty or invalid. The fetched data is then saved back to the cache.
 *
 * @param DomainType The final, clean domain model type that the UI will consume.
 * @param DtoType The Data Transfer Object type returned by the network API.
 *
 * @param query A lambda function that returns a Flow of the data from the local cache.
 * @param fetch A suspend lambda function that performs the network request and returns the DTO.
 * @param saveFetchResult A suspend lambda function that saves the fetched DTO to the local cache.
 * @param shouldFetch A lambda that determines if a network fetch is required based on the cached data.
 * @param mapDtoToDomain A lambda to map the network DTO to the clean Domain model.
 */
inline fun <reified DomainType, reified DtoType> networkBoundResource(
    crossinline query: () -> Flow<ResultState>,
    crossinline fetch: suspend () -> ResultState,
    crossinline saveFetchResult: suspend (DtoType) -> Unit,
    crossinline shouldFetch: (DomainType?) -> Boolean = { it == null },
    crossinline mapDtoToDomain: (DtoType) -> DomainType,
    crossinline onCachePresent: () -> Unit = {}
) = flow {
    emit(ResultState.Running("Checking cache..."))
    val cachedDataResult = query().last()

    var cachedData: DomainType? = null
    cachedDataResult.parse<DomainType>(
        onError = { Timber.w(it, "Cache returned an error.") }
    ) { cachedData = it }

    if (shouldFetch(cachedData)) {
        emit(ResultState.Running("Cache miss. Fetching from remote..."))

        when (val remoteResult = fetch()) {
            is ResultState.Complete<*> -> {
                val dto = remoteResult.data as? DtoType

                if (dto != null) {
                    emit(ResultState.Complete(mapDtoToDomain(dto)))

                    supervisorScope {
                        launch {
                            try {
                                saveFetchResult(dto)
                            } catch (e: Exception) {
                                Timber.e(e, "Failed to save network resource to cache.")
                            }
                        }
                    }
                } else {
                    emit(
                        ResultState.Error(
                            IllegalStateException("Type mismatch: Remote fetch did not return the expected DTO.")
                        )
                    )
                }
            }

            is ResultState.Error -> {
                emit(remoteResult)
            }

            is ResultState.Running -> {
                emit(remoteResult)
            }
        }
    } else {
        onCachePresent()
        emit(ResultState.Complete(cachedData!!))
    }
}