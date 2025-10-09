package io.bitbot.bemusedbaboon.domain.usecase

import io.bitbot.bemusedbaboon.data.repository.pokemoncount.PokemonCountRepo
import io.bitbot.bemusedbaboon.data.repository.pokemoncount.PokemonCountRepoLocal
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
class GetPokemonCount(private val local: PokemonCountRepoLocal, private val remote: PokemonCountRepo) :
    UseCase<Int>() {
    suspend operator fun invoke() {
        local.getPokemonCount().collect { lResult ->
            lResult.parseApiResult<Int>(
                onError = { e -> e.update(null) },
            ) { count ->
                count.validate(
                    onSuccess = { validCount -> validCount.update() },
                    onFail = {
                        remote.getPokemonCount().collect { rResult ->
                            rResult.parseApiResult<Int>(
                                onError = { e -> e.update(null) },
                            ) { remoteCount ->
                                remoteCount.validate { validCount ->
                                    local.setPokemonCount(validCount).collect { saveResult ->
                                        saveResult.parseApiResult<Int>(
                                            onError = { e -> Timber.e(e) }
                                        ) { savedCount ->
                                            Timber.i("Pokemon count %d saved", savedCount)
                                        }
                                    }
                                    validCount.update()
                                }
                            }
                        }
                    })
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