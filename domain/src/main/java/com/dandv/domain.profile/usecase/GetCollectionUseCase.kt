package com.dandv.domain.profile.usecase

import com.dandv.domain.common.UseCaseEmptyInput
import com.dandv.domain.profile.entity.collection.CollectionEntity
import com.dandv.domain.profile.entity.collection.CollectionType
import com.dandv.domain.profile.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

class GetCollectionUseCase
@Inject constructor(
    private val profileRepository: ProfileRepository
) : UseCaseEmptyInput<Flow<CollectionEntity>> {

    override suspend fun buildUseCase(): Flow<CollectionEntity> {
        return profileRepository.getCollectionType().flatMapConcat {
            when (it) {
                CollectionType.SKILLS -> profileRepository.getSkills()
                CollectionType.EXPERIENCES -> profileRepository.getExperiences()
                CollectionType.PROJECTS -> profileRepository.getProjects()
            }
        }
    }
}
