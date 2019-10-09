package com.dandv.spike.ui.home

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.dandv.domain.profile.entity.ProfileData
import com.dandv.domain.profile.entity.ProfileEntity
import com.dandv.domain.profile.entity.collection.CollectionType
import com.dandv.domain.profile.usecase.GetProfileUseCase
import com.dandv.domain.profile.usecase.SetCollectionTypeUseCase
import com.dandv.spike.common.CoroutineScopeTestFactory
import com.dandv.spike.ui.home.mapper.ProfileDataToHomePageUiModelMapper
import com.dandv.spike.ui.home.model.HomePageUiModel
import com.dandv.spike.ui.home.model.HomePageViewState
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

class HomePageViewModelTest {

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getProfileUseCase: GetProfileUseCase
    @Mock
    private lateinit var setCollectionTypeUseCase: SetCollectionTypeUseCase
    @Mock
    private lateinit var profileDataToHomePageUiModelMapper: ProfileDataToHomePageUiModelMapper
    @Mock
    private lateinit var pageViewStateObserver: Observer<HomePageViewState>

    private lateinit var cut: HomePageViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        cut = HomePageViewModel(
            getProfileUseCase,
            setCollectionTypeUseCase,
            profileDataToHomePageUiModelMapper,
            CoroutineScopeTestFactory(),

        )
        cut.getPageViewState().observeForever(pageViewStateObserver)
    }

    @Test
    fun `given error profile when requestProfile then Error view state posted`() {
        runBlocking {
            // given
            given(getProfileUseCase.buildUseCase()).willReturn(ProfileEntity.Error)

            // when
            cut.requestProfile()

            // then
            verify(getProfileUseCase).buildUseCase()
            verify(profileDataToHomePageUiModelMapper, never()).mapToPresentation(any())
            val captor = ArgumentCaptor.forClass(HomePageViewState::class.java)
            verify(pageViewStateObserver, times(2)).onChanged(capture(captor))
            assertTrue(captor.value is HomePageViewState.Error)
        }
    }

    @Test
    fun `given empty profile when requestProfile then Error view state posted`() {
        runBlocking {
            // given
            given(getProfileUseCase.buildUseCase()).willReturn(ProfileEntity.Empty)

            // when
            cut.requestProfile()

            // then
            verify(getProfileUseCase).buildUseCase()
            verify(profileDataToHomePageUiModelMapper, never()).mapToPresentation(any())
            val captor = ArgumentCaptor.forClass(HomePageViewState::class.java)
            verify(pageViewStateObserver, times(2)).onChanged(capture(captor))
            assertTrue(captor.value is HomePageViewState.Error)
        }
    }

    @Test
    fun `given valid profile when requestProfile then Success view state posted`() {
        runBlocking {
            // given
            val profileData = mock<ProfileData>()
            given(getProfileUseCase.buildUseCase()).willReturn(ProfileEntity.Data(profileData))
            val homePageUiModel = mock<HomePageUiModel>()
            given(profileDataToHomePageUiModelMapper.mapToPresentation(profileData)).willReturn(homePageUiModel)

            // when
            cut.requestProfile()

            // then
            verify(getProfileUseCase).buildUseCase()
            verify(profileDataToHomePageUiModelMapper).mapToPresentation(profileData)
            val captor = ArgumentCaptor.forClass(HomePageViewState::class.java)
            verify(pageViewStateObserver, times(2)).onChanged(capture(captor))
            val value = captor.value
            assertTrue(value is HomePageViewState.Success)
            assertEquals(homePageUiModel, value.homePageUiModel)
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
            val captor = ArgumentCaptor.forClass(HomePageViewState::class.java)
            verify(pageViewStateObserver).onChanged(capture(captor))
            val value = captor.value
            assertTrue(value is HomePageViewState.Navigation)
        }
    }
}