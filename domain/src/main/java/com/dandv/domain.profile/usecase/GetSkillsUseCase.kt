package com.dandv.domain.profile.usecase

import com.dandv.domain.common.UseCaseEmptyInput
import com.dandv.domain.profile.entity.collection.CollectionEntity
import com.dandv.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class GetSkillsUseCase
@Inject constructor(private val profileRepository: ProfileRepository): UseCaseEmptyInput<CollectionEntity> {

    override suspend fun buildUseCase(): CollectionEntity {
        return profileRepository.getSkills()
    }
}