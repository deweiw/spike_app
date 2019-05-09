package com.dandv.data.profile.repository

import com.dandv.data.profile.datasource.ProfileRemoteDataSource
import com.dandv.domain.profile.entity.ProfileEntity
import com.dandv.domain.profile.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRepositoryImpl
@Inject constructor(private val profileRemoteDataSource: ProfileRemoteDataSource) : ProfileRepository {

    override suspend fun getProfile(): ProfileEntity {
        return withContext(Dispatchers.IO) {
            profileRemoteDataSource.getProfile()
        }
    }
}