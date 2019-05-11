package com.dandv.spike.ui.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.dandv.domain.profile.entity.collection.CollectionType
import com.dandv.spike.R
import com.dandv.spike.common.toVisibility
import com.dandv.spike.ui.collection.CollectionDetailActivity
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

        skills_layout.setOnClickListener {
            homePageViewModel.requestCollection(CollectionType.SKILLS)
        }

        project_layout.setOnClickListener {
            homePageViewModel.requestCollection(CollectionType.PROJECTS)
        }

        experience_layout.setOnClickListener {
            homePageViewModel.requestCollection(CollectionType.EXPERIENCES)
        }

    }

    override fun onResume() {
        super.onResume()
        homePageViewModel.requestProfile()
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

    private fun setupViewModel() {
        homePageViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomePageViewModel::class.java)
    }
}
