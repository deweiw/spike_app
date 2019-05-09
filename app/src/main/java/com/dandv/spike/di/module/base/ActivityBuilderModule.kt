package com.dandv.spike.di.module.base

import com.dandv.spike.di.module.ui.MainScreenModule
import com.dandv.spike.ui.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @PerScreen
    @ContributesAndroidInjector(modules = [MainScreenModule::class])
    abstract fun bindMainActivity(): MainActivity

}