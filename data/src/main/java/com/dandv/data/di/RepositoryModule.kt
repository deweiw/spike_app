package com.dandv.data.di

import android.app.Application
import androidx.room.Room
import com.dandv.data.common.AppDatabase
import com.dandv.data.profile.datasource.local.ProfileDao
import com.dandv.data.profile.datasource.local.ProfileLocalDataSource
import com.dandv.data.profile.datasource.remote.ProfileRemoteDataSource
import com.dandv.data.profile.repository.ProfileRepositoryImpl
import com.dandv.domain.common.di.DefaultDispatcherProvider
import com.dandv.domain.common.di.DispatcherProvider
import com.dandv.domain.profile.repository.ProfileRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    internal fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    internal fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            "profile_db"
        ).build()
    }

    @Singleton
    @Provides
    internal fun provideProfileDao(appDatabase: AppDatabase): ProfileDao {
        return appDatabase.profileDao()
    }

    @Singleton
    @Provides
    internal fun provideProfileRepository(
        profileLocalDataSource: ProfileLocalDataSource,
        profileRemoteDataSource: ProfileRemoteDataSource,
        dispatcherProvider: DispatcherProvider
    ): ProfileRepository {
        return ProfileRepositoryImpl(
            profileLocalDataSource,
            profileRemoteDataSource,
            dispatcherProvider
        )
    }

    @Provides
    internal fun provideDispatcherProvider(): DispatcherProvider {
        return DefaultDispatcherProvider()
    }
}
