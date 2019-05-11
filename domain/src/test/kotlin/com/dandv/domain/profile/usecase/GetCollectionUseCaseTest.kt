package com.dandv.domain.profile.usecase

import com.dandv.domain.profile.entity.collection.CollectionEntity
import com.dandv.domain.profile.entity.collection.CollectionType
import com.dandv.domain.profile.repository.ProfileRepository
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetCollectionUseCaseTest {

    @Mock
    private lateinit var profileRepository: ProfileRepository
    private lateinit var cut: GetCollectionUseCase

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        cut = GetCollectionUseCase(profileRepository)
    }

    @Test
    fun `given collection type as Skills when buildUseCase called then getSkills called in repo`() {
        runBlocking {
            // given
            given(profileRepository.getCollectionType()).willReturn(CollectionType.SKILLS)
            val collectionEntity = mock<CollectionEntity>()
            given(profileRepository.getSkills()).willReturn(collectionEntity)

            // when
            val result = cut.buildUseCase()

            // then
            verify(profileRepository).getCollectionType()
            verify(profileRepository).getSkills()
            verify(profileRepository, never()).getExperiences()
            verify(profileRepository, never()).getProjects()
            assertEquals(collectionEntity, result)
        }
    }

    @Test
    fun `given collection type as EXPERIENCES when buildUseCase called then getExperiences called in repo`() {
        runBlocking {
            // given
            given(profileRepository.getCollectionType()).willReturn(CollectionType.EXPERIENCES)
            val collectionEntity = mock<CollectionEntity>()
            given(profileRepository.getExperiences()).willReturn(collectionEntity)

            // when
            val result = cut.buildUseCase()

            // then
            verify(profileRepository).getCollectionType()
            verify(profileRepository, never()).getSkills()
            verify(profileRepository).getExperiences()
            verify(profileRepository, never()).getProjects()
            assertEquals(collectionEntity, result)
        }
    }

    @Test
    fun `given collection type as PROJECTS when buildUseCase called then getProjects called in repo`() {
        runBlocking {
            // given
            given(profileRepository.getCollectionType()).willReturn(CollectionType.PROJECTS)
            val collectionEntity = mock<CollectionEntity>()
            given(profileRepository.getProjects()).willReturn(collectionEntity)

            // when
            val result = cut.buildUseCase()

            // then
            verify(profileRepository).getCollectionType()
            verify(profileRepository, never()).getSkills()
            verify(profileRepository, never()).getExperiences()
            verify(profileRepository).getProjects()
            assertEquals(collectionEntity, result)
        }
    }
}