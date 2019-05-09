package com.dandv.data.di

import com.dandv.data.profile.datasource.ProfileRemoteDataSource
import com.dandv.data.profile.repository.ProfileRepositoryImpl
import com.dandv.domain.profile.repository.ProfileRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    internal fun provideProfileRepository(profileRemoteDataSource: ProfileRemoteDataSource): ProfileRepository {
        return ProfileRepositoryImpl(profileRemoteDataSource)
    }
}