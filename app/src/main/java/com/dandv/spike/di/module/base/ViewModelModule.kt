package com.dandv.spike.di.module.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.dandv.spike.ViewModelFactory
import com.dandv.spike.ui.collection.CollectionPageViewModel
import com.dandv.spike.ui.home.HomePageViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomePageViewModel::class)
    abstract fun bindHomePageViewModel(homePageViewModel: HomePageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CollectionPageViewModel::class)
    abstract fun bindCollectionPageViewModel(collectionPageViewModel: CollectionPageViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}