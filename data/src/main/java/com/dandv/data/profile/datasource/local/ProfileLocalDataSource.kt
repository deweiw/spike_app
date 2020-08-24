package com.dandv.data.profile.datasource.local

import com.dandv.data.profile.datasource.local.mapper.ProfileEntityToProfileRoomDtoMapper
import com.dandv.data.profile.datasource.local.mapper.ProfileRoomDtoToProfileEntityMapper
import com.dandv.domain.profile.entity.ProfileEntity

/**
 * For demo purpose, only profile data saved into the local database. If we want to save other information into the local database,
 * we can create other tables and do the similar jobs as the one implemented here
 */
class ProfileLocalDataSource constructor(
    private val profileDao: ProfileDao,
    private val profileRoomDtoToProfileEntityMapper: ProfileRoomDtoToProfileEntityMapper,
    private val profileEntityToProfileRoomDtoMapper: ProfileEntityToProfileRoomDtoMapper
) {

    fun getProfile(): ProfileEntity {
        return try {
            profileRoomDtoToProfileEntityMapper.mapToDomain(profileDao.getProfile())
        } catch (error: Throwable) {
            ProfileEntity.Error
        }
    }

    fun saveProfile(profileEntity: ProfileEntity) {
        when (profileEntity) {
            is ProfileEntity.Data -> profileDao.saveProfile(
                profileEntityToProfileRoomDtoMapper.mapToData(profileEntity.profileData)
            )
        }
    }
}