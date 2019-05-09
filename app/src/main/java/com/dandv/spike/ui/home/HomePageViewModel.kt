package com.dandv.spike.ui.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.dandv.domain.profile.entity.ProfileEntity
import com.dandv.domain.profile.usecase.GetProfileUseCase
import com.dandv.spike.common.BaseViewModel
import com.dandv.spike.common.CoroutineScopeFactory
import com.dandv.spike.ui.home.mapper.ProfileDataToHomePageUiModelMapper
import com.dandv.spike.ui.home.model.HomePageViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomePageViewModel
@Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
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
}