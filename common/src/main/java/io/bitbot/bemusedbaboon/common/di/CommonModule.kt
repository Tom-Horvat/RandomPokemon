package io.bitbot.bemusedbaboon.common.di

import io.bitbot.bemusedbaboon.common.database.provideDatabase
import io.bitbot.bemusedbaboon.data.PokemonDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val commonModule = module {
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
}