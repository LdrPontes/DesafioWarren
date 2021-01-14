package me.ldrpontes.warrenbrasil

import android.app.Application
import me.ldrpontes.data.di.databaseModule
import me.ldrpontes.data.di.networkingModule
import me.ldrpontes.data.di.repositoryModule
import me.ldrpontes.warrenbrasil.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            if (BuildConfig.DEBUG) androidLogger(Level.DEBUG)
            modules(dataModules)
        }
    }

    private val dataModules = listOf(appModule, networkingModule, repositoryModule, databaseModule)

}