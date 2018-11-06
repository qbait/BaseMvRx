package co.digitalheart.basemvrx.core

import android.app.Application
import co.digitalheart.basemvrx.di.networkModule
import org.koin.android.ext.android.startKoin


class MvRxApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(networkModule))
    }
}