package com.dandv.data.profile.datasource

import com.dandv.data.common.NetworkClient
import com.dandv.data.profile.mapper.ProfileDtoToProfileEntityMapper
import com.dandv.data.profile.mapper.collection.SkillDtoToSkillDataMapper
import com.dandv.domain.profile.entity.ProfileEntity
import com.dandv.domain.profile.entity.collection.CollectionEntity
import javax.inject.Inject

class ProfileRemoteDataSource
@Inject constructor(
    private val networkClient: NetworkClient,
    private val profileDtoToProfileEntityMapper: ProfileDtoToProfileEntityMapper,
    private val skillDtoToSkillDataMapper: SkillDtoToSkillDataMapper
) {
    fun getProfile(): ProfileEntity {
        val response = networkClient.getProfile().execute()
        return when (response.isSuccessful) {
            true -> response.body()?.let { profileDtoToProfileEntityMapper.mapToDomain(it) } ?: ProfileEntity.Empty
            else -> ProfileEntity.Error
        }
    }

    fun getSkills(): CollectionEntity {
        val response = networkClient.getSkills().execute()
        return when (response.isSuccessful) {
            true -> response.body()?.let {
                CollectionEntity.SkillCollection(it.map { SkillDto ->
                    skillDtoToSkillDataMapper.mapToDomain(SkillDto)
                })
            } ?: CollectionEntity.Empty
            else -> CollectionEntity.Error
        }
    }
}