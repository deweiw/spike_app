package com.dandv.spike.ui.collection

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import com.dandv.spike.components.LoadingProgressBar
import com.dandv.spike.components.collection.ExperiencesList
import com.dandv.spike.components.collection.ProjectList
import com.dandv.spike.components.collection.SkillsList
import com.dandv.spike.ui.collection.model.CollectionPageViewState
import dagger.hilt.android.AndroidEntryPoint

/**
 * From CollectionDetailActivity, user can check details of Skills, Projects and Experiences.
 * The different type of collections share the same recycler view and adapter
 * but injected with different view holder corresponding to each type of the collection item object
 *
 * Due to the demo purpose, the error handling view is not implemented, only a log created
 */
@AndroidEntryPoint
class CollectionDetailActivity : AppCompatActivity() {

    private val collectionPageViewModel: CollectionPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initCollectionView()
    }

    override fun onResume() {
        super.onResume()
        collectionPageViewModel.requestCollectionData()
    }

    private fun initCollectionView() {
        lifecycleScope.launchWhenStarted {
            collectionPageViewModel.collectionPageViewState.collect {
                onPageStateChanged(it)
            }
        }
    }

    private fun onPageStateChanged(collectionPageViewState: CollectionPageViewState?) {
        setContent {
            collectionPageViewState?.let {
                when (it) {
                    CollectionPageViewState.Loading -> LoadingProgressBar(true)
                    CollectionPageViewState.Error -> HandleErrorView()
                    is CollectionPageViewState.Skills -> SkillsList(it.skills)
                    is CollectionPageViewState.Experiences -> ExperiencesList(it.experiences)
                    is CollectionPageViewState.Projects -> ProjectList(it.projects)
                }
            }
        }
    }

    @Composable
    private fun HandleErrorView() {
        LoadingProgressBar(false)
        // TODO Can show an error view here
        Log.e("CollectionActivity", "error")
    }
}
