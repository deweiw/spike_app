package com.dandv.spike.ui.home.model

import android.graphics.Bitmap

sealed class HomePageViewState {
    object Loading : HomePageViewState()
    object Error : HomePageViewState()
    object Navigation : HomePageViewState()
    data class Success(val homePageUiModel: HomePageUiModel) : HomePageViewState()
    data class ImageLoadedSuccess(val bitmap: Bitmap?): HomePageViewState()
    object ImageLoadedFailed: HomePageViewState()
}