package com.dandv.spike.di

import android.arch.lifecycle.ViewModelProvider
import com.dandv.spike.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class TestViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}