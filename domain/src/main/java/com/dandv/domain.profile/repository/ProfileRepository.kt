package com.dandv.domain.profile.repository

import com.dandv.domain.profile.entity.ProfileEntity
import com.dandv.domain.profile.entity.collection.CollectionEntity
import com.dandv.domain.profile.entity.collection.CollectionType
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    /**
     * @return [ProfileEntity] when user requests Profile data.
     *
     * When request with error, return [ProfileEntity.Error]
     * When request profile is an empty profile, return [ProfileEntity.Empty]
     * When request profile is a valid profile, return [ProfileEntity.Data]
     */
    suspend fun getProfile(): Flow<ProfileEntity>

    /**
     * Set collection type in order to request the remote collection data details
     *
     * @param collectionType, three types can be set: Skills, Projects and Experiences
     */
    suspend fun setCollectionType(collectionType: CollectionType)

    /**
     * Get the collection type which user want to request from the remote collection data
     *
     * @return [CollectionType]
     */
    suspend fun getCollectionType(): Flow<CollectionType>

    /**
     * Get list of Skills
     *
     * @return [CollectionEntity.Error] if request failed, [CollectionEntity.Empty] if request an empty list and
     * [CollectionEntity.SkillCollection] if request list with Skills
     */
    suspend fun getSkills(): Flow<CollectionEntity>

    /**
     * Get list of Projects
     *
     * @return [CollectionEntity.Error] if request failed, [CollectionEntity.Empty] if request an empty list and
     * [CollectionEntity.ProjectCollection] if request list with Projects
     */
    suspend fun getProjects(): Flow<CollectionEntity>

    /**
     * Get list of Experiences
     *
     * @return [CollectionEntity.Error] if request failed, [CollectionEntity.Empty] if request an empty list and
     * [CollectionEntity.ExperienceCollection] if request list with Experiences
     */
    suspend fun getExperiences(): Flow<CollectionEntity>
}
