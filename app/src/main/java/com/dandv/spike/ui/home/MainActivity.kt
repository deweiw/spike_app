package com.dandv.spike.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import coil.annotation.ExperimentalCoilApi
import com.dandv.spike.R
import com.dandv.spike.components.LoadingProgressBar
import com.dandv.spike.components.ProfileView
import com.dandv.spike.ui.BaseActivity
import com.dandv.spike.ui.collection.CollectionDetailActivity
import com.dandv.spike.ui.home.model.HomePageUiModel
import com.dandv.spike.ui.home.model.HomePageViewState
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Main activity is the home activity, which shows Profile information.
 * When launch the main activity, request profile data, show it if the data comes back successfully. If profile contains
 * available skills, projects or experiences. User is able to click it and open the detail list page.
 *
 * Due to the demo purpose, the error handling view is not implemented, only a log created
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

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

    private fun onPageStateChanged(homePageViewState: HomePageViewState?) {
        setContent {
            homePageViewState?.let {
                when (it) {
                    HomePageViewState.Loading -> LoadingProgressBar(true)
                    is HomePageViewState.Success -> HandleContentView(it.homePageUiModel)
                    HomePageViewState.Error -> HandleErrorView()
                    HomePageViewState.Navigation -> navigateToCollectionPage()
                }
            }
        }
    }

    private fun initializeViews() {
        lifecycleScope.launchWhenStarted {
            homePageViewModel.pageViewState.collect {
                onPageStateChanged(it)
            }
        }
    }

    @Composable
    private fun HandleErrorView() {
        LoadingProgressBar(false)
        // TODO Can show an error view here
        Log.e("MainActivity", "error")
    }

    private fun navigateToCollectionPage() {
        startActivity(Intent(this, CollectionDetailActivity::class.java))
    }

    @ExperimentalCoilApi
    @Composable
    private fun HandleContentView(homePageUiModel: HomePageUiModel) {
        LoadingProgressBar(false)
        ProfileView(homePageUiModel)
    }
}
