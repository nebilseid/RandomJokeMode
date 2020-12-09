package com.example.moderandomjokes

import android.app.Application
import com.example.moderandomjokes.model.di.networkModule
import com.example.moderandomjokes.model.di.repo
import com.example.moderandomjokes.model.di.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, viewModels, repo))
        }
    }
}