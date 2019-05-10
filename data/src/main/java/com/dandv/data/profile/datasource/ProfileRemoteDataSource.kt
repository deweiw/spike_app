package com.dandv.data.profile.datasource

import com.dandv.data.common.NetworkClient
import com.dandv.data.profile.mapper.ProfileDtoToProfileEntityMapper
import com.dandv.data.profile.mapper.collection.ExperienceDtoToExperienceDataMapper
import com.dandv.data.profile.mapper.collection.ProjectDtoToProjectDataMapper
import com.dandv.data.profile.mapper.collection.SkillDtoToSkillDataMapper
import com.dandv.domain.profile.entity.ProfileEntity
import com.dandv.domain.profile.entity.collection.CollectionEntity
import javax.inject.Inject

class ProfileRemoteDataSource
@Inject constructor(
    private val networkClient: NetworkClient,
    private val profileDtoToProfileEntityMapper: ProfileDtoToProfileEntityMapper,
    private val skillDtoToSkillDataMapper: SkillDtoToSkillDataMapper,
    private val projectDtoToProjectDataMapper: ProjectDtoToProjectDataMapper,
    private val experienceDtoToExperienceDataMapper: ExperienceDtoToExperienceDataMapper
) {
    fun getProfile(): ProfileEntity {
        return try {
            val response = networkClient.getProfile().execute()
            when (response.isSuccessful) {
                true -> response.body()?.let { profileDtoToProfileEntityMapper.mapToDomain(it) } ?: ProfileEntity.Empty
                else -> ProfileEntity.Error
            }
        } catch (error: Exception) {
            ProfileEntity.Error
        }
    }

    fun getSkills(): CollectionEntity {
        return try {
            val response = networkClient.getSkills().execute()
            when (response.isSuccessful) {
                true -> response.body()?.let {
                    CollectionEntity.SkillCollection(it.map { skillDto ->
                        skillDtoToSkillDataMapper.mapToDomain(skillDto)
                    })
                } ?: CollectionEntity.Empty
                else -> CollectionEntity.Error
            }
        } catch (error: Exception) {
            CollectionEntity.Error
        }
    }

    fun getProjects(): CollectionEntity {
        return try {
            val response = networkClient.getProjects().execute()
            when (response.isSuccessful) {
                true -> response.body()?.let {
                    CollectionEntity.ProjectCollection(it.map { projectDto ->
                        projectDtoToProjectDataMapper.mapToDomain(projectDto)
                    })
                } ?: CollectionEntity.Empty
                else -> CollectionEntity.Error
            }
        } catch (error: Exception) {
            CollectionEntity.Error
        }
    }

    fun getExperiences(): CollectionEntity {
        return try {
            val response = networkClient.getExperiences().execute()
            when (response.isSuccessful) {
                true -> response.body()?.let {
                    CollectionEntity.ExperienceCollection(it.map { experienceDto ->
                        experienceDtoToExperienceDataMapper.mapToDomain(experienceDto)
                    })
                } ?: CollectionEntity.Empty
                else -> CollectionEntity.Error
            }
        } catch (error: Exception) {
            CollectionEntity.Error
        }
    }
}