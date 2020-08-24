package com.dandv.spike.ui.collection

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.dandv.domain.profile.entity.collection.CollectionEntity
import com.dandv.domain.profile.entity.collection.ExperienceData
import com.dandv.domain.profile.entity.collection.ProjectData
import com.dandv.domain.profile.entity.collection.SkillData
import com.dandv.domain.profile.usecase.GetCollectionUseCase
import com.dandv.spike.common.CoroutineScopeTestFactory
import com.dandv.spike.ui.collection.mapper.ExperienceDataToExperienceItemUiModelMapper
import com.dandv.spike.ui.collection.mapper.ProjectDataToProjectItemUiModelMapper
import com.dandv.spike.ui.collection.mapper.SkillDataToSkillItemUiModelMapper
import com.dandv.spike.ui.collection.model.CollectionPageViewState
import com.dandv.spike.ui.collection.model.ExperienceItemUiModel
import com.dandv.spike.ui.collection.model.ProjectItemUiModel
import com.dandv.spike.ui.collection.model.SkillItemUiModel
import com.nhaarman.mockito_kotlin.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CollectionPageViewModelTest {

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getCollectionUseCase: GetCollectionUseCase

    @Mock
    private lateinit var skillDataToSkillItemUiModelMapper: SkillDataToSkillItemUiModelMapper

    @Mock
    private lateinit var projectDataToProjectItemUiModelMapper: ProjectDataToProjectItemUiModelMapper

    @Mock
    private lateinit var experienceDataToExperienceItemUiModelMapper: ExperienceDataToExperienceItemUiModelMapper

    @Mock
    private lateinit var collectionPageViewState: Observer<CollectionPageViewState>

    private lateinit var cut: CollectionPageViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        cut = CollectionPageViewModel(
            getCollectionUseCase,
            skillDataToSkillItemUiModelMapper,
            projectDataToProjectItemUiModelMapper,
            experienceDataToExperienceItemUiModelMapper,
            CoroutineScopeTestFactory()
        )
        cut.getCollectionPageViewState().observeForever(collectionPageViewState)
    }

    @Test
    fun `given empty collection when requestCollectionData then Error view state triggered`() {
        runBlocking {
            // given
            given(getCollectionUseCase.buildUseCase()).willReturn(CollectionEntity.Empty)

            // when
            cut.requestCollectionData()

            // then
            verify(getCollectionUseCase).buildUseCase()
            val captor = ArgumentCaptor.forClass(CollectionPageViewState::class.java)
            verify(collectionPageViewState, times(2)).onChanged(capture(captor))
            assertTrue(captor.value is CollectionPageViewState.Error)
        }
    }

    @Test
    fun `given error collection when requestCollectionData then Error view state triggered`() {
        runBlocking {
            // given
            given(getCollectionUseCase.buildUseCase()).willReturn(CollectionEntity.Error)

            // when
            cut.requestCollectionData()

            // then
            verify(getCollectionUseCase).buildUseCase()
            val captor = ArgumentCaptor.forClass(CollectionPageViewState::class.java)
            verify(collectionPageViewState, times(2)).onChanged(capture(captor))
            assertTrue(captor.value is CollectionPageViewState.Error)
        }
    }

    @Test
    fun `given collection of Skills when requestCollectionData then view state with Skills triggered`() {
        runBlocking {
            // given
            val skillData = mock<SkillData>()
            val skills = listOf(skillData)
            given(getCollectionUseCase.buildUseCase()).willReturn(
                CollectionEntity.SkillCollection(
                    skills
                )
            )
            val skillItemUiModel = mock<SkillItemUiModel>()
            given(skillDataToSkillItemUiModelMapper.mapToPresentation(skillData)).willReturn(
                skillItemUiModel
            )

            // when
            cut.requestCollectionData()

            // then
            verify(getCollectionUseCase).buildUseCase()
            verify(skillDataToSkillItemUiModelMapper).mapToPresentation(skillData)
            val captor = ArgumentCaptor.forClass(CollectionPageViewState::class.java)
            verify(collectionPageViewState, times(2)).onChanged(capture(captor))
            val value = captor.value
            assertTrue(value is CollectionPageViewState.Skills)
            assertEquals(1, value.skills.size)
            assertEquals(skillItemUiModel, value.skills[0])
        }
    }

    @Test
    fun `given collection of Project when requestCollectionData then view state with Projects triggered`() {
        runBlocking {
            // given
            val projectData = mock<ProjectData>()
            val projects = listOf(projectData)
            given(getCollectionUseCase.buildUseCase()).willReturn(
                CollectionEntity.ProjectCollection(
                    projects
                )
            )
            val projectItemUiModel = mock<ProjectItemUiModel>()
            given(projectDataToProjectItemUiModelMapper.mapToPresentation(projectData)).willReturn(
                projectItemUiModel
            )

            // when
            cut.requestCollectionData()

            // then
            verify(getCollectionUseCase).buildUseCase()
            verify(projectDataToProjectItemUiModelMapper).mapToPresentation(projectData)
            val captor = ArgumentCaptor.forClass(CollectionPageViewState::class.java)
            verify(collectionPageViewState, times(2)).onChanged(capture(captor))
            val value = captor.value
            assertTrue(value is CollectionPageViewState.Projects)
            assertEquals(1, value.projects.size)
            assertEquals(projectItemUiModel, value.projects[0])
        }
    }

    @Test
    fun `given ExperienceCollection when requestCollectionData then view state with Experience triggered`() {
        runBlocking {
            // given
            val experienceData = mock<ExperienceData>()
            val experiences = listOf(experienceData)
            given(getCollectionUseCase.buildUseCase()).willReturn(
                CollectionEntity.ExperienceCollection(
                    experiences
                )
            )
            val experienceItemUiModel = mock<ExperienceItemUiModel>()
            given(experienceDataToExperienceItemUiModelMapper.mapToPresentation(experienceData)).willReturn(
                experienceItemUiModel
            )

            // when
            cut.requestCollectionData()

            // then
            verify(getCollectionUseCase).buildUseCase()
            verify(experienceDataToExperienceItemUiModelMapper).mapToPresentation(experienceData)
            val captor = ArgumentCaptor.forClass(CollectionPageViewState::class.java)
            verify(collectionPageViewState, times(2)).onChanged(capture(captor))
            val value = captor.value
            assertTrue(value is CollectionPageViewState.Experiences)
            assertEquals(1, value.experiences.size)
            assertEquals(experienceItemUiModel, value.experiences[0])
        }
    }
}