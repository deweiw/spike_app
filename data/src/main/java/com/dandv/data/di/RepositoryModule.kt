package com.dandv.data.di

import android.app.Application
import android.arch.persistence.room.Room
import com.dandv.data.common.AppDatabase
import com.dandv.data.profile.datasource.remote.ProfileRemoteDataSource
import com.dandv.data.profile.datasource.local.ProfileDao
import com.dandv.data.profile.datasource.local.ProfileLocalDataSource
import com.dandv.data.profile.repository.ProfileRepositoryImpl
import com.dandv.domain.profile.repository.ProfileRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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
        return Room.databaseBuilder(application.applicationContext, AppDatabase::class.java, "profile_db").build()
    }

    @Singleton
    @Provides
    internal fun provideProfileDao(appDatabase: AppDatabase): ProfileDao {
        return appDatabase.profileDao()
    }

    @Singleton
    @Provides
    internal fun provideProfileRepository(
        profileLocalDataSource: ProfileLocalDataSource, profileRemoteDataSource: ProfileRemoteDataSource
    ): ProfileRepository {
        return ProfileRepositoryImpl(profileLocalDataSource, profileRemoteDataSource)
    }
}