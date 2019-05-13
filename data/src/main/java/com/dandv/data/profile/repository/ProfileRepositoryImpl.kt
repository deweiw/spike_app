package com.dandv.data.profile.repository

import com.dandv.data.profile.datasource.local.ProfileLocalDataSource
import com.dandv.data.profile.datasource.remote.ProfileRemoteDataSource
import com.dandv.domain.profile.entity.ProfileEntity
import com.dandv.domain.profile.entity.collection.CollectionEntity
import com.dandv.domain.profile.entity.collection.CollectionType
import com.dandv.domain.profile.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * This repository implements repo interface defined in the domain layer.
 *
 * Normally use case defines which data it wanted, repo implementation defines where and how to get the data
 */
class ProfileRepositoryImpl(
    private val profileLocalDataSource: ProfileLocalDataSource,
    private val profileRemoteDataSource: ProfileRemoteDataSource
) : ProfileRepository {

    private lateinit var collectionType: CollectionType

    /** The current logic is: Getting the profile from the local database first, if there is no local database or
     * any error happened, request the data from the server and save it to the local database.
     *
     *  Here is the place to implement the logic of how to interact between local and remote data source,
     *  for example we can force to refresh the data from the remote by adding a flag and triggered
     *  by user using pull to refresh function.
     */
    override suspend fun getProfile(): ProfileEntity {
        return withContext(Dispatchers.IO) {
            val profileEntity = profileLocalDataSource.getProfile()
            when (profileEntity) {
                is ProfileEntity.Data -> profileEntity
                else -> {
                    profileRemoteDataSource.getProfile().also {
                        profileLocalDataSource.saveProfile(it)
                    }
                }
            }
        }
    }

    override suspend fun setCollectionType(collectionType: CollectionType) {
        this.collectionType = collectionType
    }

    override suspend fun getCollectionType(): CollectionType {
        return collectionType
    }

    override suspend fun getSkills(): CollectionEntity {
        return withContext(Dispatchers.IO) {
            profileRemoteDataSource.getSkills()
        }
    }

    override suspend fun getProjects(): CollectionEntity {
        return withContext(Dispatchers.IO) {
            profileRemoteDataSource.getProjects()
        }
    }

    override suspend fun getExperiences(): CollectionEntity {
        return withContext(Dispatchers.IO) {
            profileRemoteDataSource.getExperiences()
        }
    }
}