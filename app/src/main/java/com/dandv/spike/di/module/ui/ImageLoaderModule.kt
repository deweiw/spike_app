package com.dandv.spike.di.module.ui

import android.app.Application
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class ImageLoaderModule {

    @Reusable
    @Provides
    fun provideImageLoader(application: Application): Picasso {
        return Picasso.Builder(application).build()
    }
}
