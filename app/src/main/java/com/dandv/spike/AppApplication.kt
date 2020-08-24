package com.dandv.spike

import android.app.Application
import com.dandv.data.di.clientModule
import com.dandv.data.di.repositoryModule
import com.dandv.di.useCaseModule
import com.dandv.spike.di.module.toolsModule
import com.dandv.spike.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin

class AppApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        initComponent()
    }

    private fun initComponent() {
        startKoin {
            androidContext(this@AppApplication)
            modules(
                listOf(
                    toolsModule, viewModelModule, clientModule, repositoryModule, useCaseModule
                )
            )
        }
    }
}