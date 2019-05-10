package com.dandv.domain.profile.usecase

import com.dandv.domain.common.UseCase
import com.dandv.domain.profile.entity.collection.CollectionType
import com.dandv.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class SetCollectionTypeUseCase
@Inject constructor(private val profileRepository: ProfileRepository): UseCase<Boolean, CollectionType> {

    override suspend fun buildUseCase(params: CollectionType): Boolean {
        profileRepository.setCollecionType(params)
        return true
    }
}