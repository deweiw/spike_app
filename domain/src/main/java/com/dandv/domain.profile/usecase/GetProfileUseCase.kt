package com.dandv.domain.profile.usecase

import com.dandv.domain.common.UseCaseEmptyInput
import com.dandv.domain.profile.entity.ProfileEntity
import com.dandv.domain.profile.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProfileUseCase
@Inject constructor(private val profileRepository: ProfileRepository) :
    UseCaseEmptyInput<Flow<ProfileEntity>> {

    override suspend fun buildUseCase(): Flow<ProfileEntity> {
        return profileRepository.getProfile()
    }
}
