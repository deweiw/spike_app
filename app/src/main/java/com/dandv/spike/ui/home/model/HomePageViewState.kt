package com.dandv.spike.ui.home.model

sealed class HomePageViewState {
    object Loading : HomePageViewState()
    object Error : HomePageViewState()
    data class Success(val homePageUiModel: HomePageUiModel) : HomePageViewState()
}