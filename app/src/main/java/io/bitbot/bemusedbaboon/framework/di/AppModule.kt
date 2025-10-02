package io.bitbot.bemusedbaboon.framework.di

import io.bitbot.bemusedbaboon.adapter.PokeIndexJasonAdapter
import io.bitbot.bemusedbaboon.adapter.PokemonJsonAdapter
import io.bitbot.bemusedbaboon.data.repository.PokemonDataRepository
import io.bitbot.bemusedbaboon.data.repository.PokemonDataSource
import io.bitbot.bemusedbaboon.framework.PrefsCache
import io.bitbot.bemusedbaboon.framework.datasource.LocalDataSourceImpl
import io.bitbot.bemusedbaboon.framework.datasource.RemoteDataSourceImpl
import io.bitbot.bemusedbaboon.framework.manager.PrefsManager
import io.bitbot.bemusedbaboon.framework.provider.provideMoshi
import io.bitbot.bemusedbaboon.framework.provider.provideOkHttp
import io.bitbot.bemusedbaboon.framework.provider.providePokemonApi
import io.bitbot.bemusedbaboon.framework.provider.provideRetrofit
import io.bitbot.bemusedbaboon.ui.viewmodel.PokemonViewModel
import io.bitbot.bemusedbaboon.usecase.CachePokeIndex
import io.bitbot.bemusedbaboon.usecase.CachePokemon
import io.bitbot.bemusedbaboon.usecase.GetPokeIndex
import io.bitbot.bemusedbaboon.usecase.GetPokemon
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * A module of all app resources that are injected
 */
val appModule = module {
    single { RemoteDataSourceImpl(api = get()) }
    single {
        LocalDataSourceImpl(
            prefsCache = get(),
            pokemonDaoOld = get(),
            adapter = get(),
        )
    }
    single<PokemonDataSource> {
        PokemonDataRepository.getInstance(
            localDataSource = get<LocalDataSourceImpl>(),
            remoteDataSource = get<RemoteDataSourceImpl>(),
        )
    }

    single { GetPokemon(dataSource = get()) }
    single { CachePokemon(dataSource = get()) }
    single { GetPokeIndex(dataSource = get()) }
    single { CachePokeIndex(dataSource = get()) }

    factory { provideOkHttp() }
    factory { provideMoshi() }
    factory {
        provideRetrofit(
            okHttpClient = get(),
            moshi = get(),
        )
    }
    single { providePokemonApi(retrofit = get()) }

    single { PokeIndexJasonAdapter(moshi = get()) }
    single { PokemonJsonAdapter(moshi = get()) }

    single<PrefsCache> {
        PrefsManager(
            context = get(),
            adapter = get(),
        )
    }

    viewModel {
        PokemonViewModel(
            getPokemon = get(),
            cachePokemon = get(),
            getPokeIndex = get(),
            cachePokeIndex = get(),
        )
    }
}