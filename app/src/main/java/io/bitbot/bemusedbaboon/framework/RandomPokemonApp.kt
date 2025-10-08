package io.bitbot.bemusedbaboon.framework

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import io.bitbot.bemusedbaboon.BuildConfig
import io.bitbot.bemusedbaboon.common.di.commonModule
import io.bitbot.bemusedbaboon.framework.di.appModule
import io.bitbot.bemusedbaboon.landing.framework.landingModule
import io.bitbot.bemusedbaboon.landing.framework.landingNav
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RandomPokemonApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@RandomPokemonApp)
            modules(
                appModule,
                commonModule,
                landingModule,
            )
        }

        ScreenRegistry {
            landingNav()
        }
    }
}