package com.aransafp.subar

import android.app.Application
import com.aransafp.core.di.databaseModule
import com.aransafp.core.di.networkModule
import com.aransafp.core.di.repositoryModule
import com.aransafp.subar.di.useCaseModule
import com.aransafp.subar.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}