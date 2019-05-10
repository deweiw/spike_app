package com.dandv.spike.ui.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.dandv.domain.profile.entity.ProfileEntity
import com.dandv.domain.profile.entity.collection.CollectionType
import com.dandv.domain.profile.usecase.GetProfileUseCase
import com.dandv.domain.profile.usecase.SetCollectionTypeUseCase
import com.dandv.spike.common.BaseViewModel
import com.dandv.spike.common.CoroutineScopeFactory
import com.dandv.spike.ui.home.mapper.ProfileDataToHomePageUiModelMapper
import com.dandv.spike.ui.home.model.HomePageViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomePageViewModel
@Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val setCollectionTypeUseCase: SetCollectionTypeUseCase,
    private val profileDataToHomePageUiModelMapper: ProfileDataToHomePageUiModelMapper,
    coroutineScopeFactory: CoroutineScopeFactory
) : BaseViewModel(coroutineScopeFactory) {

    private val pageViewState = MutableLiveData<HomePageViewState>()

    fun getPageViewState(): LiveData<HomePageViewState> = pageViewState

    fun requestProfile() {
        pageViewState.postValue(HomePageViewState.Loading)
        coroutineScope.launch {
            val profile = getProfileUseCase.buildUseCase()
            when (profile) {
                is ProfileEntity.Data -> pageViewState.postValue(
                    HomePageViewState.Success(
                        profileDataToHomePageUiModelMapper.mapToPresentation(profile.profileData)
                    )
                )
                ProfileEntity.Empty,
                ProfileEntity.Error -> pageViewState.postValue(HomePageViewState.Error)
            }
        }
    }

    fun requestCollection(collectionType: CollectionType) {
        coroutineScope.launch {
            setCollectionTypeUseCase.buildUseCase(collectionType)
            pageViewState.postValue(HomePageViewState.Navigation)
        }
    }
}