package com.garciafrancisco.pokedex


import android.app.Application
import timber.log.Timber


class PokedexApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            //Debug Mode
            Timber.plant(Timber.DebugTree())
        } else {
            //Release Mode
            //TODO Init crashlytics here
        }
    }
}