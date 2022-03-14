package com.dandv.spike.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dandv.domain.profile.entity.ProfileData
import com.dandv.domain.profile.entity.ProfileEntity
import com.dandv.domain.profile.entity.collection.CollectionType
import com.dandv.domain.profile.usecase.GetProfileUseCase
import com.dandv.domain.profile.usecase.SetCollectionTypeUseCase
import com.dandv.spike.common.TestCoroutineScopeFactory
import com.dandv.spike.ui.home.mapper.ProfileDataToHomePageUiModelMapper
import com.dandv.spike.ui.home.model.HomePageUiModel
import com.dandv.spike.ui.home.model.HomePageViewState
import com.nhaarman.mockito_kotlin.*
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

class HomePageViewModelTest {

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getProfileUseCase: GetProfileUseCase

    @Mock
    private lateinit var setCollectionTypeUseCase: SetCollectionTypeUseCase

    @Mock
    private lateinit var profileDataToHomePageUiModelMapper: ProfileDataToHomePageUiModelMapper

    private val testCoroutineScopeFactory = TestCoroutineScopeFactory()

    private lateinit var cut: HomePageViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        cut = HomePageViewModel(
            getProfileUseCase,
            setCollectionTypeUseCase,
            profileDataToHomePageUiModelMapper,
            testCoroutineScopeFactory
        )
//        cut.pageViewState.observeForever(pageViewStateObserver)
    }

    @Test
    fun `given error profile when requestProfile then Error view state posted`() {
        runBlocking {
            // given
            given(getProfileUseCase.buildUseCase()).willReturn(flowOf(ProfileEntity.Error))

            // when
            cut.requestProfile()

            // then
            verify(getProfileUseCase).buildUseCase()
            verify(profileDataToHomePageUiModelMapper, never()).mapToPresentation(any())
            var state: HomePageViewState? = null
            testCoroutineScopeFactory.getScope().launch {
                cut.pageViewState.collect {
                    state = it
                }
            }

            assertTrue { state is HomePageViewState.Error }
        }
    }

    @Test
    fun `given empty profile when requestProfile then Error view state posted`() {
        runBlocking {
            // given
            given(getProfileUseCase.buildUseCase()).willReturn(flowOf(ProfileEntity.Empty))

            // when
            cut.requestProfile()

            // then
            verify(getProfileUseCase).buildUseCase()
            verify(profileDataToHomePageUiModelMapper, never()).mapToPresentation(any())
            var state: HomePageViewState? = null
            testCoroutineScopeFactory.getScope().launch {
                cut.pageViewState.collect {
                    state = it
                }
            }
            assertTrue { state is HomePageViewState.Error }
        }
    }

    @Test
    fun `given valid profile when requestProfile then Success view state posted`() {
        runBlocking {
            // given
            val profileData = mock<ProfileData>()
            given(getProfileUseCase.buildUseCase()).willReturn(flowOf(ProfileEntity.Data(profileData)))
            val homePageUiModel = mock<HomePageUiModel>()
            given(profileDataToHomePageUiModelMapper.mapToPresentation(profileData)).willReturn(
                homePageUiModel
            )

            // when
            cut.requestProfile()

            // then
            verify(getProfileUseCase).buildUseCase()
            verify(profileDataToHomePageUiModelMapper).mapToPresentation(profileData)
            var state: HomePageViewState? = null
            testCoroutineScopeFactory.getScope().launch {
                cut.pageViewState.collect {
                    state = it
                }
            }
            assertTrue { state is HomePageViewState.Success }
            assertEquals(homePageUiModel, (state as HomePageViewState.Success).homePageUiModel)
        }
    }

    @Test
    fun `when requestCollection then navigate event triggered`() {
        runBlocking {
            // given
            val collectionType = mock<CollectionType>()

            // when
            cut.requestCollection(collectionType)

            // then
            verify(setCollectionTypeUseCase).buildUseCase(collectionType)
            var state: HomePageViewState? = null
            testCoroutineScopeFactory.getScope().launch {
                cut.pageViewState.collect {
                    state = it
                }
            }
            assertTrue(state is HomePageViewState.Navigation)
        }
    }
}
