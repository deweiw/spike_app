package com.dandv.di

import com.dandv.domain.profile.usecase.GetCollectionUseCase
import com.dandv.domain.profile.usecase.GetProfileUseCase
import com.dandv.domain.profile.usecase.SetCollectionTypeUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetCollectionUseCase(profileRepository = get()) }
    factory { GetProfileUseCase(profileRepository = get()) }
    factory { SetCollectionTypeUseCase(profileRepository = get()) }
}