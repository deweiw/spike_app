package com.dandv.spike.ui.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.graphics.Bitmap
import com.dandv.domain.profile.entity.ProfileEntity
import com.dandv.domain.profile.entity.collection.CollectionType
import com.dandv.domain.profile.usecase.GetProfileUseCase
import com.dandv.domain.profile.usecase.SetCollectionTypeUseCase
import com.dandv.spike.common.BaseViewModel
import com.dandv.spike.common.CoroutineScopeFactory
import com.dandv.spike.tools.ImageProcessingTarget
import com.dandv.spike.tools.ImageProcessingTargetFactory
import com.dandv.spike.ui.home.mapper.ProfileDataToHomePageUiModelMapper
import com.dandv.spike.ui.home.model.HomePageViewState
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class HomePageViewModel
@Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val setCollectionTypeUseCase: SetCollectionTypeUseCase,
    private val profileDataToHomePageUiModelMapper: ProfileDataToHomePageUiModelMapper,
    private val picasso: Picasso,
    private val imageProcessingTargetFactory: ImageProcessingTargetFactory,
    coroutineScopeFactory: CoroutineScopeFactory
) : BaseViewModel(coroutineScopeFactory), ImageProcessingTarget.Callback {

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

    fun requestHomeImage(imageUrl: String, placeHolder: Int) {
        picasso.load(imageUrl).placeholder(placeHolder).into(imageProcessingTargetFactory.getImageProcessingTarget(this))
    }

    override fun onBitmapFailed(e: Exception) {
        pageViewState.postValue(HomePageViewState.ImageLoadedFailed)
    }

    override fun onBitmapLoaded(bitmap: Bitmap?) {
        pageViewState.postValue(HomePageViewState.ImageLoadedSuccess(bitmap))
    }
}