package com.dandv.domain.profile.repository

import com.dandv.domain.profile.entity.ProfileEntity
import com.dandv.domain.profile.entity.collection.CollectionEntity
import com.dandv.domain.profile.entity.collection.CollectionType

interface ProfileRepository {

    suspend fun getProfile(): ProfileEntity

    suspend fun setCollecionType(collectionType: CollectionType)

    suspend fun getCollectionType(): CollectionType

    suspend fun getSkills(): CollectionEntity

    suspend fun getProjects(): CollectionEntity

    suspend fun getExperiences(): CollectionEntity
}