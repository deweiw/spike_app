package com.dandv.spike.di.module.ui

import android.app.Activity
import com.dandv.spike.ui.collection.CollectionDetailActivity
import dagger.Module
import dagger.Provides

@Module
class CollectionScreenModule {

    @Provides
    fun provideCollectionScreenView(collectionDetailActivity: CollectionDetailActivity): Activity {
        return collectionDetailActivity
    }
}