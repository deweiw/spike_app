package com.dandv.data.profile.datasource.remote

import com.dandv.data.common.NetworkClient
import com.dandv.data.profile.datasource.remote.mapper.ProfileDtoToProfileEntityMapper
import com.dandv.data.profile.datasource.remote.mapper.collection.ExperienceDtoToExperienceDataMapper
import com.dandv.data.profile.datasource.remote.mapper.collection.ProjectDtoToProjectDataMapper
import com.dandv.data.profile.datasource.remote.mapper.collection.SkillDtoToSkillDataMapper
import com.dandv.domain.common.di.DispatcherProvider
import com.dandv.domain.profile.entity.ProfileEntity
import com.dandv.domain.profile.entity.collection.CollectionEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Using Retrofit to request remote data here.
 */
class ProfileRemoteDataSource
@Inject constructor(
    private val networkClient: NetworkClient,
    private val profileDtoToProfileEntityMapper: ProfileDtoToProfileEntityMapper,
    private val skillDtoToSkillDataMapper: SkillDtoToSkillDataMapper,
    private val projectDtoToProjectDataMapper: ProjectDtoToProjectDataMapper,
    private val experienceDtoToExperienceDataMapper: ExperienceDtoToExperienceDataMapper,
    private val dispatcherProvider: DispatcherProvider
) {
    fun getProfile(): Flow<ProfileEntity> {
        return flow {
            val result = try {
                val response = networkClient.getProfile().execute()
                when (response.isSuccessful) {
                    true -> response.body()?.let { profileDtoToProfileEntityMapper.mapToDomain(it) }
                        ?: ProfileEntity.Empty
                    else -> ProfileEntity.Error
                }
            } catch (error: Exception) {
                ProfileEntity.Error
            }
            emit(result)
        }.flowOn(dispatcherProvider.io)
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
