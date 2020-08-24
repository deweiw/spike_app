package com.dandv.spike.di.module

import android.app.Application
import com.dandv.spike.ui.collection.adapter.CollectionPageAdapterFactory
import com.dandv.spike.ui.collection.mapper.ExperienceDataToExperienceItemUiModelMapper
import com.dandv.spike.ui.collection.viewholder.ViewHolderFactory
import com.squareup.picasso.Picasso
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val toolsModule = module {
    single { provideImageLoader(androidApplication()) }
    single { provideExperienceDataToExperienceItemUiModelMapper() }
    factory { ViewHolderFactory() }
    factory { CollectionPageAdapterFactory() }
}

private fun provideImageLoader(application: Application): Picasso {
    return Picasso.Builder(application).build()
}

private fun provideExperienceDataToExperienceItemUiModelMapper(): ExperienceDataToExperienceItemUiModelMapper {
    return ExperienceDataToExperienceItemUiModelMapper(StringBuilder())
}