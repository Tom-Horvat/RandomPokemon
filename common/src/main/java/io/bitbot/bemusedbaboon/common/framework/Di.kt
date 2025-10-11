package io.bitbot.bemusedbaboon.common.framework

import io.bitbot.bemusedbaboon.common.database.provideDatabase
import io.bitbot.bemusedbaboon.common.manager.PokemonIndexLocalRepoImpl
import io.bitbot.bemusedbaboon.common.manager.PokemonIndexRemoteRepoImpl
import io.bitbot.bemusedbaboon.common.provider.provideMoshi
import io.bitbot.bemusedbaboon.common.provider.provideOkHttp
import io.bitbot.bemusedbaboon.common.provider.providePokemonApi
import io.bitbot.bemusedbaboon.common.provider.provideRetrofit
import io.bitbot.bemusedbaboon.core.data.PokemonDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val common = module {
    single { provideDatabase(androidContext()) }
    single { get<PokemonDatabase>().abilityDao() }
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

    single {
        PokemonIndexLocalRepoImpl(
            context = androidContext(),
            indexDao = get()
        )
    }
    single { PokemonIndexRemoteRepoImpl(api = get()) }

    factory { provideMoshi() }
    factory { provideOkHttp() }
    factory { provideRetrofit(okHttpClient = get(), moshi = get()) }
    factory { providePokemonApi(retrofit = get()) }
}