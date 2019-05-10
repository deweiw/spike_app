package com.dandv.data.profile.repository

import com.dandv.data.profile.datasource.ProfileRemoteDataSource
import com.dandv.domain.profile.entity.ProfileEntity
import com.dandv.domain.profile.entity.collection.CollectionEntity
import com.dandv.domain.profile.entity.collection.CollectionType
import com.dandv.domain.profile.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRepositoryImpl(private val profileRemoteDataSource: ProfileRemoteDataSource) : ProfileRepository {

    private lateinit var collectionType: CollectionType

    override suspend fun getProfile(): ProfileEntity {
        return withContext(Dispatchers.IO) {
            profileRemoteDataSource.getProfile()
        }
    }

    override suspend fun setCollecionType(collectionType: CollectionType) {
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
}