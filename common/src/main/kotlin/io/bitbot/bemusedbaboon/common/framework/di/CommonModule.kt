package io.bitbot.bemusedbaboon.common.framework.di

import io.bitbot.bemusedbaboon.common.api.provideMoshi
import io.bitbot.bemusedbaboon.common.api.provideOkHttp
import io.bitbot.bemusedbaboon.common.api.providePokemonApi
import io.bitbot.bemusedbaboon.common.api.provideRetrofit
import io.bitbot.bemusedbaboon.common.dispatchers.StandardDispatchers
import io.bitbot.bemusedbaboon.common.logging.TimberLogger
import io.bitbot.bemusedbaboon.common.repository.index.IndexRepoImpl
import io.bitbot.bemusedbaboon.common.repository.index.IndexRepoLocalImpl
import io.bitbot.bemusedbaboon.common.repository.index.IndexRepoRemoteImpl
import io.bitbot.bemusedbaboon.common.repository.pokemon.PokemonRepoImpl
import io.bitbot.bemusedbaboon.common.repository.pokemon.PokemonRepoLocalImpl
import io.bitbot.bemusedbaboon.common.repository.pokemon.PokemonRepoRemoteImpl
import io.bitbot.bemusedbaboon.common.repository.provideDatabase
import io.bitbot.bemusedbaboon.core.data.PokemonDatabase
import io.bitbot.bemusedbaboon.core.data.repository.index.IndexRepo
import io.bitbot.bemusedbaboon.core.data.repository.index.InitializationProvider
import io.bitbot.bemusedbaboon.core.data.repository.pokemon.PokemonRepo
import io.bitbot.bemusedbaboon.core.domain.dispatchers.DispatcherProvider
import io.bitbot.bemusedbaboon.core.domain.usecase.Logger
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.binds
import org.koin.dsl.module

val common = module {
    single<DispatcherProvider> { StandardDispatchers() }
    single<Logger> { TimberLogger() }
    single { provideDatabase(androidContext()) }
    single { get<PokemonDatabase>().abilityDao() }
    single { get<PokemonDatabase>().abilitySlotDao() }
    single { get<PokemonDatabase>().cryDao() }
    single { get<PokemonDatabase>().moveDao() }
    single { get<PokemonDatabase>().speciesDao() }
    single { get<PokemonDatabase>().spritesDao() }
    single { get<PokemonDatabase>().statDao() }
    single { get<PokemonDatabase>().statSlotDao() }
    single { get<PokemonDatabase>().typeDao() }
    single { get<PokemonDatabase>().typeSlotDao() }
    single { get<PokemonDatabase>().pokemonDao() }
    single { get<PokemonDatabase>().indexDao() }

    single<IndexRepo.Local> {
        IndexRepoLocalImpl(
            context = androidContext(),
            indexDao = get()
        )
    }
    single {
        IndexRepoImpl(
            local = get(),
            remote = get(),
            dispatchers = get()
        )
    } binds arrayOf(IndexRepo::class, InitializationProvider::class)
    single<IndexRepo.Remote> { IndexRepoRemoteImpl(api = get()) }

    single<PokemonRepo> {
        PokemonRepoImpl(
            local = get(),
            remote = get(),
            dispatchers = get()
        )
    }
    single<PokemonRepo.Local> {
        PokemonRepoLocalImpl(
            pokemonDao = get(),
            criesDao = get(),
            spritesDao = get(),
            abilitySlotDao = get(),
        )
    }
    single<PokemonRepo.Remote> { PokemonRepoRemoteImpl(api = get()) }

    factory { provideMoshi() }
    factory { provideOkHttp() }
    factory { provideRetrofit(okHttpClient = get(), moshi = get()) }
    factory { providePokemonApi(retrofit = get()) }
}