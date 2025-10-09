package io.bitbot.bemusedbaboon.common.framework

import io.bitbot.bemusedbaboon.common.adapter.PokeIndexJasonAdapter
import io.bitbot.bemusedbaboon.common.database.provideDatabase
import io.bitbot.bemusedbaboon.common.manager.PokemonCountLocalRepoImpl
import io.bitbot.bemusedbaboon.common.manager.PokemonCountRemoteRepoImpl
import io.bitbot.bemusedbaboon.common.provider.provideMoshi
import io.bitbot.bemusedbaboon.common.provider.provideOkHttp
import io.bitbot.bemusedbaboon.common.provider.providePokemonApi
import io.bitbot.bemusedbaboon.common.provider.provideRetrofit
import io.bitbot.bemusedbaboon.data.PokemonDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val common = module {
    single { provideDatabase(androidContext()) }
    single { get<PokemonDatabase>().pokemonDaoOld() }
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

    single { PokemonCountLocalRepoImpl(context = androidContext()) }
    single { PokemonCountRemoteRepoImpl(api = get()) }

    factory { provideMoshi() }
    single { PokeIndexJasonAdapter(moshi = get()) }
    factory { provideOkHttp() }
    factory { provideRetrofit(okHttpClient = get(), moshi = get()) }
    factory { providePokemonApi(retrofit = get()) }
}