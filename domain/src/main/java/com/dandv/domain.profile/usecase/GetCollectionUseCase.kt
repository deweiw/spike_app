package com.dandv.domain.profile.usecase

import com.dandv.domain.common.UseCaseEmptyInput
import com.dandv.domain.profile.entity.collection.CollectionEntity
import com.dandv.domain.profile.entity.collection.CollectionType
import com.dandv.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class GetCollectionUseCase
@Inject constructor(
    private val profileRepository: ProfileRepository,
    private val getSkillsUseCase: GetSkillsUseCase
) : UseCaseEmptyInput<CollectionEntity> {

    override suspend fun buildUseCase(): CollectionEntity {
        val collectionType = profileRepository.getCollectionType()
        return when (collectionType) {
            CollectionType.SKILLS -> getSkillsUseCase.buildUseCase()
            else -> CollectionEntity.Empty
        }
    }
}