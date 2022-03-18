package com.dandv.data.profile.datasource.local

import com.dandv.data.profile.datasource.local.mapper.ProfileEntityToProfileRoomDtoMapper
import com.dandv.data.profile.datasource.local.mapper.ProfileRoomDtoToProfileEntityMapper
import com.dandv.domain.common.di.DispatcherProvider
import com.dandv.domain.profile.entity.ProfileEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * For demo purpose, only profile data saved into the local database. If we want to save other information into the local database,
 * we can create other tables and do the similar jobs as the one implemented here
 */
class ProfileLocalDataSource
@Inject constructor(
    private val profileDao: ProfileDao,
    private val profileRoomDtoToProfileEntityMapper: ProfileRoomDtoToProfileEntityMapper,
    private val profileEntityToProfileRoomDtoMapper: ProfileEntityToProfileRoomDtoMapper,
    private val dispatcherProvider: DispatcherProvider
) {

    fun getProfile(): Flow<ProfileEntity> {
        return profileDao.getProfile().map { profileRoomDtoToProfileEntityMapper.mapToDomain(it) }
            .flowOn(dispatcherProvider.io)
            .catch { emit(ProfileEntity.Error) }
    }

    fun saveProfile(profileEntity: ProfileEntity) {
        (profileEntity as? ProfileEntity.Data)?.let {
            profileDao.saveProfile(profileEntityToProfileRoomDtoMapper.mapToData(it.profileData))
        }
    }
}
