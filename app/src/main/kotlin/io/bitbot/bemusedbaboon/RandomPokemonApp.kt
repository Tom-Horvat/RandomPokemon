package io.bitbot.bemusedbaboon

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import io.bitbot.bemusedbaboon.common.framework.di.common
import io.bitbot.bemusedbaboon.framework.di.app
import io.bitbot.bemusedbaboon.landing.framework.navigation.landingNav
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RandomPokemonApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.Forest.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@RandomPokemonApp)
            modules(
                app,
                common,
            )
        }

        ScreenRegistry {
            landingNav()
        }
    }
}