package com.dandv.spike.di.module

import com.dandv.spike.common.CoroutineScopeFactory
import com.dandv.spike.ui.collection.CollectionPageViewModel
import com.dandv.spike.ui.collection.mapper.ProjectDataToProjectItemUiModelMapper
import com.dandv.spike.ui.collection.mapper.SkillDataToSkillItemUiModelMapper
import com.dandv.spike.ui.home.HomePageViewModel
import com.dandv.spike.ui.home.mapper.ProfileDataToHomePageUiModelMapper
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { CoroutineScopeFactory() }
    factory { ProfileDataToHomePageUiModelMapper() }
    factory { SkillDataToSkillItemUiModelMapper() }
    factory { ProjectDataToProjectItemUiModelMapper() }
    viewModel { HomePageViewModel(get(), get(), get(), coroutineScopeFactory = get()) }
    viewModel { CollectionPageViewModel(get(), get(), get(), get(), coroutineScopeFactory = get()) }
}