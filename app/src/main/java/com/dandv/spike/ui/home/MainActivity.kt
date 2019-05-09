package com.dandv.spike.ui.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.View
import com.dandv.spike.R
import com.dandv.spike.common.toVisibility
import com.dandv.spike.ui.home.model.HomePageUiModel
import com.dandv.spike.ui.home.model.HomePageViewState
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var picasso: Picasso

    private lateinit var homePageViewModel: HomePageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()

        homePageViewModel.getPageViewState().observe(this, Observer {
            onPageStateChanged(it)
        })
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        homePageViewModel.requestProfile()
    }

    private fun onPageStateChanged(homePageViewState: HomePageViewState?) {
        homePageViewState?.let {
            when (it) {
                HomePageViewState.Loading -> handleLoadingView()
                is HomePageViewState.Success -> handleContentView(it.homePageUiModel)
                HomePageViewState.Error -> handleErrorView()
            }
        }
    }

    private fun handleErrorView() {
        // TODO Can show an error view here
        Log.i(MainActivity::class.simpleName, "error")
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

    private fun setupViewModel() {
        homePageViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomePageViewModel::class.java)
    }
}
