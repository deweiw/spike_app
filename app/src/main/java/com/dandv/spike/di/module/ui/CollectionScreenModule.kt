package com.dandv.spike.di.module.ui

import android.app.Activity
import com.dandv.spike.ui.collection.CollectionDetailActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class CollectionScreenModule {

    @Provides
    fun provideCollectionScreenView(collectionDetailActivity: CollectionDetailActivity): Activity {
        return collectionDetailActivity
    }
}
