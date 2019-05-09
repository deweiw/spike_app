package com.dandv.spike.di.module.ui

import android.app.Activity
import com.dandv.spike.ui.home.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainScreenModule {

    @Provides
    fun provideMainScreenView(mainActivity: MainActivity): Activity {
        return mainActivity
    }
}
