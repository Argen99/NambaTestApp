package com.example.nambatestapp.app

import android.app.Application
import com.example.nambatestapp.app.di.appModule
import com.example.nambatestapp.data.di.dataModule
import com.example.nambatestapp.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, dataModule, domainModule))
        }
    }
}