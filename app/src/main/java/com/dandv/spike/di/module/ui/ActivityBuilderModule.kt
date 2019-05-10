package com.dandv.spike.di.module.ui

import com.dandv.spike.di.module.base.PerScreen
import com.dandv.spike.ui.collection.CollectionDetailActivity
import com.dandv.spike.ui.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @PerScreen
    @ContributesAndroidInjector(modules = [MainScreenModule::class])
    abstract fun bindMainActivity(): MainActivity

    @PerScreen
    @ContributesAndroidInjector(modules = [CollectionScreenModule::class])
    abstract fun bindCollectionDetailActivity(): CollectionDetailActivity
}