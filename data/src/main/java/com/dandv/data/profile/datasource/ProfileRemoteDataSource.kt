package com.dandv.data.profile.datasource

import com.dandv.data.common.NetworkClient
import com.dandv.data.profile.mapper.ProfileDtoToProfileEntityMapper
import com.dandv.domain.profile.entity.ProfileEntity
import javax.inject.Inject

class ProfileRemoteDataSource
@Inject constructor(
    private val networkClient: NetworkClient,
    private val profileDtoToProfileEntityMapper: ProfileDtoToProfileEntityMapper
) {
    fun getProfile(): ProfileEntity {
        val response = networkClient.getProfile().execute()
        return when (response.isSuccessful) {
            true -> response.body()?.let { profileDtoToProfileEntityMapper.mapToDomain(it) } ?: ProfileEntity.Empty
            else -> ProfileEntity.Error
        }
    }
}