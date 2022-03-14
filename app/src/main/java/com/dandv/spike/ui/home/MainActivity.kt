package com.dandv.spike.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.dandv.domain.profile.entity.collection.CollectionType
import com.dandv.spike.R
import com.dandv.spike.common.toVisibility
import com.dandv.spike.ui.BaseActivity
import com.dandv.spike.ui.collection.CollectionDetailActivity
import com.dandv.spike.ui.home.model.HomePageUiModel
import com.dandv.spike.ui.home.model.HomePageViewState
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * Main activity is the home activity, which shows Profile information.
 * When launch the main activity, request profile data, show it if the data comes back successfully. If profile contains
 * available skills, projects or experiences. User is able to click it and open the detail list page.
 *
 * Due to the demo purpose, the error handling view is not implemented, only a log created
 */
@AndroidEntryPoint
class MainActivity : BaseActivity(), View.OnClickListener {

    @Inject
    lateinit var picasso: Picasso

    @VisibleForTesting
    internal val homePageViewModel: HomePageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()
    }

    override fun onResume() {
        super.onResume()
        homePageViewModel.requestProfile()
    }

    override fun onClick(v: View) {
        when (v) {
            skills_layout -> homePageViewModel.requestCollection(CollectionType.SKILLS)
            project_layout -> homePageViewModel.requestCollection(CollectionType.PROJECTS)
            experience_layout -> homePageViewModel.requestCollection(CollectionType.EXPERIENCES)
        }
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun observeViewModelState() {
        lifecycleScope.launchWhenStarted {
            homePageViewModel.pageViewState.collect {
                onPageStateChanged(it)
            }
        }
    }

    private fun onPageStateChanged(homePageViewState: HomePageViewState?) {
        homePageViewState?.let {
            when (it) {
                HomePageViewState.Loading -> handleLoadingView()
                is HomePageViewState.Success -> handleContentView(it.homePageUiModel)
                HomePageViewState.Error -> handleErrorView()
                HomePageViewState.Navigation -> navigateToCollectionPage()
            }
        }
    }

    private fun initializeViews() {
        skills_layout.setOnClickListener(this)
        project_layout.setOnClickListener(this)
        experience_layout.setOnClickListener(this)
    }

    private fun handleErrorView() {
        // TODO Can show an error view here
        progress_bar.visibility = View.GONE
        Log.e("MainActivity", "error")
    }

    private fun navigateToCollectionPage() {
        startActivity(Intent(this, CollectionDetailActivity::class.java))
    }

    private fun handleLoadingView() {
        progress_bar.visibility = View.VISIBLE
        content_layout.visibility = View.GONE
    }

    private fun handleContentView(homePageUiModel: HomePageUiModel) {
        progress_bar.visibility = View.GONE
        content_layout.visibility = View.VISIBLE
        with(homePageUiModel) {
            picasso.load(imageUrl).placeholder(R.drawable.profile_placeholder).into(profile_image)
            candidate_name.text = name
            candidate_phone.text = phone
            candidate_email.text = email
            candidate_summary.text = summary
            skills_layout.visibility = anySkill.toVisibility()
            experience_layout.visibility = anyExperience.toVisibility()
            project_layout.visibility = anyProjects.toVisibility()
        }
    }
}
