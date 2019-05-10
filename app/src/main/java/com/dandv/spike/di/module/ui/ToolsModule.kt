package com.dandv.spike.di.module.ui

import android.app.Application
import com.dandv.spike.ui.collection.mapper.ExperienceDataToExperienceItemUiModelMapper
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class ToolsModule {

    @Reusable
    @Provides
    fun provideImageLoader(application: Application): Picasso {
        return Picasso.Builder(application).build()
    }

    @Reusable
    @Provides
    fun provideExperienceDataToExperienceItemUiModelMapper(): ExperienceDataToExperienceItemUiModelMapper {
        return ExperienceDataToExperienceItemUiModelMapper(StringBuilder())
    }
}
