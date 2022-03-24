package com.dandv.spike.ui.home

import com.dandv.domain.profile.entity.ProfileEntity
import com.dandv.domain.profile.entity.collection.CollectionType
import com.dandv.domain.profile.usecase.GetProfileUseCase
import com.dandv.domain.profile.usecase.SetCollectionTypeUseCase
import com.dandv.spike.common.BaseViewModel
import com.dandv.spike.common.CoroutineScopeFactory
import com.dandv.spike.ui.home.mapper.ProfileDataToHomePageUiModelMapper
import com.dandv.spike.ui.home.model.HomePageViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel
@Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val setCollectionTypeUseCase: SetCollectionTypeUseCase,
    private val profileDataToHomePageUiModelMapper: ProfileDataToHomePageUiModelMapper,
    coroutineScopeFactory: CoroutineScopeFactory
) : BaseViewModel(coroutineScopeFactory) {

    private val _pageViewStateFlow = MutableStateFlow<HomePageViewState>(HomePageViewState.Loading)
    val pageViewState: Flow<HomePageViewState> = _pageViewStateFlow

    fun requestProfile() {
        coroutineScope.launch {
            getProfileUseCase.buildUseCase().collect { profileEntity ->
                when (profileEntity) {
                    is ProfileEntity.Data -> _pageViewStateFlow.update {
                        HomePageViewState.Success(
                            profileDataToHomePageUiModelMapper.mapToPresentation(profileEntity.profileData)
                        )
                    }

                    else -> {
                        _pageViewStateFlow.emit(HomePageViewState.Error)
                    }
                }
            }
        }
    }

    fun requestCollection(collectionType: CollectionType) {
        coroutineScope.launch {
            setCollectionTypeUseCase.buildUseCase(collectionType)
            _pageViewStateFlow.emit(HomePageViewState.Navigation)
        }
    }
}
