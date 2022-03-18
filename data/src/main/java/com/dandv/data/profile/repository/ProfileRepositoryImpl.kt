package com.dandv.data.profile.repository

import com.dandv.data.profile.datasource.local.ProfileLocalDataSource
import com.dandv.data.profile.datasource.remote.ProfileRemoteDataSource
import com.dandv.domain.common.di.DispatcherProvider
import com.dandv.domain.profile.entity.ProfileEntity
import com.dandv.domain.profile.entity.collection.CollectionEntity
import com.dandv.domain.profile.entity.collection.CollectionType
import com.dandv.domain.profile.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

/**
 * This repository implements repo interface defined in the domain layer.
 *
 * Normally use case defines which data it wanted, repo implementation defines where and how to get the data
 */
class ProfileRepositoryImpl(
    private val profileLocalDataSource: ProfileLocalDataSource,
    private val profileRemoteDataSource: ProfileRemoteDataSource,
    private val dispatcherProvider: DispatcherProvider
) : ProfileRepository {

    private lateinit var collectionType: CollectionType

    /** The current logic is: Getting the profile from the local database first, if there is no local database or
     * any error happened, request the data from the server and save it to the local database.
     *
     *  Here is the place to implement the logic of how to interact between local and remote data source,
     *  for example we can force to refresh the data from the remote by adding a flag and triggered
     *  by user using pull to refresh function.
     */
    override suspend fun getProfile(): Flow<ProfileEntity> {
        return profileLocalDataSource.getProfile().flatMapConcat { profileEntity ->
            if (profileEntity is ProfileEntity.Data) {
                flowOf(profileEntity)
            } else {
                profileRemoteDataSource.getProfile()
                    .also { profileLocalDataSource.saveProfile(it.first()) }
            }
        }.flowOn(dispatcherProvider.io)
    }

    override suspend fun setCollectionType(collectionType: CollectionType) {
        this.collectionType = collectionType
    }

    override suspend fun getCollectionType(): Flow<CollectionType> {
        return flowOf(collectionType)
    }

    override suspend fun getSkills(): Flow<CollectionEntity> {
        return flow {
            val value = profileRemoteDataSource.getSkills()
            emit(value)
        }.flowOn(dispatcherProvider.io)
    }

    override suspend fun getProjects(): Flow<CollectionEntity> {
        return flow {
            emit(profileRemoteDataSource.getProjects())
        }.flowOn(dispatcherProvider.io)
    }

    override suspend fun getExperiences(): Flow<CollectionEntity> {
        return flow {
            emit(profileRemoteDataSource.getExperiences())
        }.flowOn(Dispatchers.IO)
    }
}
