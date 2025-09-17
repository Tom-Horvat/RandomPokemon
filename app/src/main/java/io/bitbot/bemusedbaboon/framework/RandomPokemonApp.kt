package io.bitbot.bemusedbaboon.framework

import android.app.Application
import io.bitbot.bemusedbaboon.BuildConfig
import io.bitbot.bemusedbaboon.framework.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * The [Application] instance that starts [Timber] and [Koin]
 */
class RandomPokemonApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@RandomPokemonApp)
            modules(appModule)
        }
    }
}