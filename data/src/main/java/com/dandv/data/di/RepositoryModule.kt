package com.dandv.data.di

import android.app.Application
import android.arch.persistence.room.Room
import com.dandv.data.common.AppDatabase
import com.dandv.data.profile.datasource.local.ProfileDao
import com.dandv.data.profile.datasource.local.ProfileLocalDataSource
import com.dandv.data.profile.datasource.local.mapper.ProfileEntityToProfileRoomDtoMapper
import com.dandv.data.profile.datasource.local.mapper.ProfileRoomDtoToProfileEntityMapper
import com.dandv.data.profile.datasource.remote.ProfileRemoteDataSource
import com.dandv.data.profile.datasource.remote.mapper.ProfileDtoToProfileEntityMapper
import com.dandv.data.profile.datasource.remote.mapper.collection.ExperienceDtoToExperienceDataMapper
import com.dandv.data.profile.datasource.remote.mapper.collection.ProjectDtoToProjectDataMapper
import com.dandv.data.profile.datasource.remote.mapper.collection.SkillDtoToSkillDataMapper
import com.dandv.data.profile.repository.ProfileRepositoryImpl
import com.dandv.domain.profile.repository.ProfileRepository
import com.google.gson.Gson
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repositoryModule = module {
    single { provideGson() }
    single { provideAppDatabase(androidApplication()) }
    single { provideProfileDao(appDatabase = get()) }
    factory { ProfileRoomDtoToProfileEntityMapper(gson = get()) }
    factory { ProfileEntityToProfileRoomDtoMapper(gson = get()) }
    factory { ProfileDtoToProfileEntityMapper() }
    factory { SkillDtoToSkillDataMapper() }
    factory { ProjectDtoToProjectDataMapper() }
    factory { ExperienceDtoToExperienceDataMapper() }
    single {
        ProfileLocalDataSource(
            profileDao = get(),
            profileRoomDtoToProfileEntityMapper = get(),
            profileEntityToProfileRoomDtoMapper = get()
        )
    }
    single {
        ProfileRemoteDataSource(
            networkClient = get(),
            profileDtoToProfileEntityMapper = get(),
            skillDtoToSkillDataMapper = get(),
            projectDtoToProjectDataMapper = get(),
            experienceDtoToExperienceDataMapper = get()
        )
    }
    single {
        provideProfileRepository(
            profileLocalDataSource = get(),
            profileRemoteDataSource = get()
        )
    }
}

private fun provideGson(): Gson {
    return Gson()
}

private fun provideAppDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(
        application.applicationContext,
        AppDatabase::class.java,
        "profile_db"
    ).build()
}

private fun provideProfileDao(appDatabase: AppDatabase): ProfileDao {
    return appDatabase.profileDao()
}

private fun provideProfileRepository(
    profileLocalDataSource: ProfileLocalDataSource, profileRemoteDataSource: ProfileRemoteDataSource
): ProfileRepository {
    return ProfileRepositoryImpl(profileLocalDataSource, profileRemoteDataSource)
}