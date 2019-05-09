package com.dandv.domain.profile.usecase

import com.dandv.domain.common.UseCaseEmptyInput
import com.dandv.domain.profile.entity.ProfileEntity
import com.dandv.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class GetProfileUseCase
@Inject constructor(private val profileRepository: ProfileRepository) : UseCaseEmptyInput<ProfileEntity> {

    override suspend fun buildUseCase(): ProfileEntity {
        return profileRepository.getProfile()
    }
}