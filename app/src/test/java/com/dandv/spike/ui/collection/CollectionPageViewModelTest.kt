package com.dandv.spike.ui.collection

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dandv.domain.profile.entity.collection.CollectionEntity
import com.dandv.domain.profile.entity.collection.ExperienceData
import com.dandv.domain.profile.entity.collection.ProjectData
import com.dandv.domain.profile.entity.collection.SkillData
import com.dandv.domain.profile.usecase.GetCollectionUseCase
import com.dandv.spike.common.TestCoroutineScopeFactory
import com.dandv.spike.ui.collection.mapper.ExperienceDataToExperienceItemUiModelMapper
import com.dandv.spike.ui.collection.mapper.ProjectDataToProjectItemUiModelMapper
import com.dandv.spike.ui.collection.mapper.SkillDataToSkillItemUiModelMapper
import com.dandv.spike.ui.collection.model.CollectionPageViewState
import com.dandv.spike.ui.collection.model.ExperienceItemUiModel
import com.dandv.spike.ui.collection.model.ProjectItemUiModel
import com.dandv.spike.ui.collection.model.SkillItemUiModel
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
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

    private val testCoroutineScopeFactory = TestCoroutineScopeFactory()
    private lateinit var cut: CollectionPageViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        cut = CollectionPageViewModel(
            getCollectionUseCase,
            skillDataToSkillItemUiModelMapper,
            projectDataToProjectItemUiModelMapper,
            experienceDataToExperienceItemUiModelMapper,
            testCoroutineScopeFactory
        )
    }

    @Test
    fun `given empty collection when requestCollectionData then Error view state triggered`() {
        runBlocking {
            // given
            given(getCollectionUseCase.buildUseCase()).willReturn(flowOf(CollectionEntity.Empty))

            // when
            cut.requestCollectionData()

            // then
            verify(getCollectionUseCase).buildUseCase()
            var state: CollectionPageViewState? = null
            testCoroutineScopeFactory.getScope().launch {
                cut.collectionPageViewState.collect {
                    state = it
                }
            }
            assertTrue { state is CollectionPageViewState.Error }
        }
    }

    @Test
    fun `given error collection when requestCollectionData then Error view state triggered`() {
        runBlocking {
            // given
            given(getCollectionUseCase.buildUseCase()).willReturn(flowOf(CollectionEntity.Error))

            // when
            cut.requestCollectionData()

            // then
            verify(getCollectionUseCase).buildUseCase()
            var state: CollectionPageViewState? = null
            testCoroutineScopeFactory.getScope().launch {
                cut.collectionPageViewState.collect {
                    state = it
                }
            }
            assertTrue { state is CollectionPageViewState.Error }
        }
    }

    @Test
    fun `given collection of Skills when requestCollectionData then view state with Skills triggered`() {
        runBlocking {
            // given
            val skillData = mock<SkillData>()
            val skills = listOf(skillData)
            given(getCollectionUseCase.buildUseCase()).willReturn(
                flowOf(
                    CollectionEntity.SkillCollection(
                        skills
                    )
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
            var state: CollectionPageViewState? = null
            testCoroutineScopeFactory.getScope().launch {
                cut.collectionPageViewState.collect {
                    state = it
                }
            }
            assertTrue { state is CollectionPageViewState.Skills }
            assertEquals(1, (state as CollectionPageViewState.Skills).skills.size)
            assertEquals(skillItemUiModel, (state as CollectionPageViewState.Skills).skills[0])
        }
    }

    @Test
    fun `given collection of Project when requestCollectionData then view state with Projects triggered`() {
        runBlocking {
            // given
            val projectData = mock<ProjectData>()
            val projects = listOf(projectData)
            given(getCollectionUseCase.buildUseCase()).willReturn(
                flowOf(
                    CollectionEntity.ProjectCollection(
                        projects
                    )
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

            var state: CollectionPageViewState? = null
            testCoroutineScopeFactory.getScope().launch {
                cut.collectionPageViewState.collect {
                    state = it
                }
            }
            assertTrue { state is CollectionPageViewState.Projects }
            assertEquals(1, (state as CollectionPageViewState.Projects).projects.size)
            assertEquals(
                projectItemUiModel,
                (state as CollectionPageViewState.Projects).projects[0]
            )
        }
    }

    @Test
    fun `given ExperienceCollection when requestCollectionData then view state with Experience triggered`() {
        runBlocking {
            // given
            val experienceData = mock<ExperienceData>()
            val experiences = listOf(experienceData)
            given(getCollectionUseCase.buildUseCase()).willReturn(
                flowOf(
                    CollectionEntity.ExperienceCollection(
                        experiences
                    )
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

            var state: CollectionPageViewState? = null
            testCoroutineScopeFactory.getScope().launch {
                cut.collectionPageViewState.collect {
                    state = it
                }
            }
            assertTrue { state is CollectionPageViewState.Experiences }
            assertEquals(1, (state as CollectionPageViewState.Experiences).experiences.size)
            assertEquals(
                experienceItemUiModel,
                (state as CollectionPageViewState.Experiences).experiences[0]
            )
        }
    }
}
