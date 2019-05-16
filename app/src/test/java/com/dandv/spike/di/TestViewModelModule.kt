package com.dandv.spike.di

import android.arch.lifecycle.ViewModel
import com.dandv.spike.di.module.base.ViewModelKey
import com.dandv.spike.ui.collection.CollectionPageViewModel
import com.dandv.spike.ui.home.HomePageViewModel
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class TestViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(HomePageViewModel::class)
    fun provideHomePageViewModel(): ViewModel {
        val homePageViewModel = mock<HomePageViewModel>()
        given(homePageViewModel.getPageViewState()).willReturn(mock())
        return homePageViewModel
    }

    @Provides
    @IntoMap
    @ViewModelKey(CollectionPageViewModel::class)
    fun provideCollectionPageViewModel(collectionPageViewModel: CollectionPageViewModel): ViewModel {
        return mock<CollectionPageViewModel>()
    }
}