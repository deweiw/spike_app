package com.dandv.spike.ui.home

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric.buildActivity
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class MainActivityTest {

    private lateinit var cut: MainActivity

    @BindValue
    val homePageViewModel = mock<HomePageViewModel>()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        hiltRule.inject()
    }

    @Test
    fun `when activity onResume then request profile through view model`() {
        // given
        val controller = buildActivity(MainActivity::class.java).setup()

        // when
        controller.resume()

        // then
        verify(controller.get().homePageViewModel).requestProfile()
    }

//    @Test
//    fun `when project_layout button clicked then request PROJECTS type of collection`() {
//        // given
//
//        // when
//        shadowOf(cut).findViewById(R.id.project_layout).performClick()
//
//        // then
//        verify(cut.homePageViewModel).requestCollection(CollectionType.PROJECTS)
//    }
//
//    @Test
//    fun `when experience_layout button clicked then request EXPERIENCES type of collection`() {
//        // given
//
//        // when
//        shadowOf(cut).findViewById(R.id.experience_layout).performClick()
//
//        // then
//        verify(cut.homePageViewModel).requestCollection(CollectionType.EXPERIENCES)
//    }
//
//    @Test
//    fun `when skill_layout button clicked then request SKILLS type of collection`() {
//        // given
//
//        // when
//        shadowOf(cut).findViewById(R.id.skills_layout).performClick()
//
//        // then
//        verify(cut.homePageViewModel).requestCollection(CollectionType.SKILLS)
//    }

//    @Test
//    fun `given page view state as Loading when observe then loading view is visible`() {
//        // given
//
//        // when
//        emitPageViewState(HomePageViewState.Loading)
//
//        // then
//        val progress = shadowOf(cut).findViewById(R.id.progress_bar)
//        assertEquals(View.VISIBLE, progress.visibility)
//        assertEquals(View.GONE, shadowOf(cut).findViewById(R.id.content_layout).visibility)
//    }
//
//    @Test
//    fun `given page view state as Error when observer then loading view is visible`() {
//        // given
//
//        // when
//        emitPageViewState(HomePageViewState.Loading)
//
//        // then
//        val progress = shadowOf(cut).findViewById(R.id.progress_bar)
//        assertEquals(View.VISIBLE, progress.visibility)
//        assertEquals(View.GONE, shadowOf(cut).findViewById(R.id.content_layout).visibility)
//    }
//
//    @Test
//    fun `given page view state as Navigation when observer then launch CollectionDetailActivity`() {
//        // given
//
//        // when
//        emitPageViewState(HomePageViewState.Navigation)
//
//        // then
//        val intent = shadowOf(cut).nextStartedActivity
//        assertEquals(CollectionDetailActivity::class.java, shadowOf(intent).intentClass)
//    }
//
//    @Test
//    fun `given page view state as successful when observer then show the content view`() {
//        // given
//        val summary = "summary"
//        val phone = "phone"
//        val name = "name"
//        val email = "email"
//        val imageUrl = "imageUrl"
//        val from = "from"
//        val to = "to"
//        val homePageUiModel = HomePageUiModel(
//            summary, phone, email, imageUrl,
//            name, from, to, anyProjects = false, anySkill = true, anyExperience = true
//        )
//        val content = HomePageViewState.Success(homePageUiModel)
//
//        // when
//        emitPageViewState(content)
//
//        // then
//        assertEquals(View.GONE, shadowOf(cut).findViewById(R.id.progress_bar).visibility)
//        assertEquals(View.VISIBLE, shadowOf(cut).findViewById(R.id.content_layout).visibility)
//        assertEquals(summary, (shadowOf(cut).findViewById(R.id.candidate_summary) as TextView).text)
//        assertEquals(phone, (shadowOf(cut).findViewById(R.id.candidate_phone) as TextView).text)
//        assertEquals(name, (shadowOf(cut).findViewById(R.id.candidate_name) as TextView).text)
//        assertEquals(email, (shadowOf(cut).findViewById(R.id.candidate_email) as TextView).text)
//        assertEquals(View.GONE, shadowOf(cut).findViewById(R.id.project_layout).visibility)
//        assertEquals(View.VISIBLE, shadowOf(cut).findViewById(R.id.skills_layout).visibility)
//        assertEquals(View.VISIBLE, shadowOf(cut).findViewById(R.id.experience_layout).visibility)
//    }

//    private fun emitPageViewState(pageViewState: HomePageViewState) {
//        argumentCaptor<Observer<HomePageViewState>>().apply {
//            verify(cut.homePageViewModel.getPageViewState()).observe(eq(cut), capture())
//            firstValue.onChanged(pageViewState)
//        }
//    }
}
