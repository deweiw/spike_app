package com.dandv.spike.ui.collection

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.View
import com.dandv.domain.profile.entity.collection.CollectionType
import com.dandv.spike.R
import com.dandv.spike.ui.collection.adapter.CollectionPageAdapter
import com.dandv.spike.ui.collection.adapter.CollectionPageAdapterFactory
import com.dandv.spike.ui.collection.mapper.CollectionItemUiModel
import com.dandv.spike.ui.collection.model.CollectionPageViewState
import com.dandv.spike.ui.collection.viewholder.ViewHolderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_collection.*
import javax.inject.Inject

class CollectionDetailActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var collectionPageAdapterFactory: CollectionPageAdapterFactory
    @Inject
    lateinit var viewHolderFactory: ViewHolderFactory

    private lateinit var collectionPageViewModel: CollectionPageViewModel
    private lateinit var collectionAdapter: CollectionPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)
        setupViewModel()

        collectionPageViewModel.getCollectionPageViewState().observe(this, Observer {
            onPageStateChanged(it)
        })

        collectionPageViewModel.requestCollectionData()
    }

    private fun onPageStateChanged(collectionPageViewState: CollectionPageViewState?) {
        collectionPageViewState?.let {
            when (it) {
                CollectionPageViewState.Loading -> handleLoadingView()
                CollectionPageViewState.Error -> handleErrorView()
                is CollectionPageViewState.Skills -> handleCollectionView(it.skills, CollectionType.SKILLS)
                is CollectionPageViewState.Experiences -> handleCollectionView(
                    it.experiences,
                    CollectionType.EXPERIENCES
                )
                is CollectionPageViewState.Projects -> handleCollectionView(it.projects, CollectionType.PROJECTS)
            }
        }
    }

    private fun handleCollectionView(list: List<CollectionItemUiModel>, collectionType: CollectionType) {
        progress_bar.visibility = View.GONE
        collection_list.visibility = View.VISIBLE
        collection_list.adapter =
            collectionPageAdapterFactory.create(collectionType, viewHolderFactory).also { collectionAdapter = it }
        collectionAdapter.updateAdapter(list)
    }

    private fun handleErrorView() {
        // TODO Can show an error view here
        progress_bar.visibility = View.GONE
        Log.e("CollectionActivity", "error")
    }

    private fun handleLoadingView() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun setupViewModel() {
        collectionPageViewModel = ViewModelProviders.of(this, viewModelFactory).get(CollectionPageViewModel::class.java)
    }
}