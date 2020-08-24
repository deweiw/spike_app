package com.dandv.domain.profile.usecase

import com.dandv.domain.common.UseCase
import com.dandv.domain.profile.entity.collection.CollectionType
import com.dandv.domain.profile.repository.ProfileRepository

class SetCollectionTypeUseCase
constructor(private val profileRepository: ProfileRepository) : UseCase<Boolean, CollectionType> {

    override suspend fun buildUseCase(params: CollectionType): Boolean {
        profileRepository.setCollectionType(params)
        return true
    }
}