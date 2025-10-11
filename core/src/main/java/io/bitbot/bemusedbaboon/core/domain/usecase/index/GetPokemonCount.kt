package io.bitbot.bemusedbaboon.core.domain.usecase.index

import io.bitbot.bemusedbaboon.core.data.repository.pokemoncount.PokemonIndexRepo
import io.bitbot.bemusedbaboon.core.data.repository.pokemoncount.PokemonIndexRepoLocal
import io.bitbot.bemusedbaboon.core.domain.usecase.UseCase
import timber.log.Timber

/**
 * @author Tom Horvat
 *
 * Gets the pokemon count from local storage. When the count is 0 the remote source is called to
 * fetch the number. When the remote source returns a number greater than 0, that number is stored
 * in the local storage ready to be fetched on subsequent runs.
 *
 * @param local An instance of a local repository.
 * @param remote An instance of a remote repository.
 */
class GetPokemonCount(
    private val local: PokemonIndexRepoLocal,
    private val remote: PokemonIndexRepo
) : UseCase<Int>() {

    /**
     * Gets the count from the local storage and validates it. When the count is valid, the use case
     * status is updated. When it is not valid an attempt is made to fetch it from the API.
     */
    suspend operator fun invoke() {
        local.getPokemonCount().collect { lResult ->
            lResult.parse<Int>(onError = { e -> e.update() }) { count ->
                count.validate(onFail = { fetchCountFromRemote() }) { validCount -> validCount.update() }
            }
        }
    }

    /**
     * Fetches the pokemon count from the API and validates the returned value. When the returned
     * count is valid, the count is saved to the local data store.
     */
    private suspend fun fetchCountFromRemote() {
        remote.getPokemonCount().collect { rResult ->
            rResult.parse<Int>(onError = { e -> e.update() }) { remoteCount ->
                remoteCount.validate(onFail = {
                    Throwable("Unable to get count from server").update()
                }) { validCount ->
                    saveCountToDataStore(validCount)
                    validCount.update()
                }
            }
        }
    }

    /**
     * Saves the given count to the local datastore.
     *
     * @param count The count to save.
     */
    private suspend fun saveCountToDataStore(count: Int) {
        local.setPokemonCount(count).collect { saveResult ->
            saveResult.parse<Int>(onError = { e -> Timber.e(e) }) { savedCount ->
                Timber.Forest.i("Pokemon count %d saved", savedCount)
            }
        }
    }

    /**
     * Validates the given integer and performs given lambdas on successful or failed validation.
     *
     * @param onFail A lambda to be run on a failed validation.
     * @param onSuccess A lambda to be run on a successful validation.
     */
    private suspend fun Int?.validate(
        onFail: suspend () -> Unit = {},
        onSuccess: suspend (Int) -> Unit
    ) {
        this?.let {
            takeIf { it > 0 }?.let { onSuccess(it) } ?: run { onFail() }
        } ?: run { onFail() }
    }
}