package com.dandv.spike.ui.collection

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.util.Log
import android.view.View
import com.dandv.domain.profile.entity.collection.CollectionType
import com.dandv.spike.R
import com.dandv.spike.ui.BaseActivity
import com.dandv.spike.ui.collection.adapter.CollectionPageAdapter
import com.dandv.spike.ui.collection.adapter.CollectionPageAdapterFactory
import com.dandv.spike.ui.collection.mapper.CollectionItemUiModel
import com.dandv.spike.ui.collection.model.CollectionPageViewState
import com.dandv.spike.ui.collection.viewholder.ViewHolderFactory
import kotlinx.android.synthetic.main.activity_collection.*
import javax.inject.Inject

/**
 * From CollectionDetailActivity, user can check details of Skills, Projects and Experiences.
 * The different type of collections share the same recycler view and adapter
 * but injected with different view holder corresponding to each type of the collection item object
 *
 * Due to the demo purpose, the error handling view is not implemented, only a log created
 */
class CollectionDetailActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var collectionPageAdapterFactory: CollectionPageAdapterFactory

    @Inject
    lateinit var viewHolderFactory: ViewHolderFactory

    private lateinit var collectionPageViewModel: CollectionPageViewModel
    private lateinit var collectionAdapter: CollectionPageAdapter

    override fun onResume() {
        super.onResume()
        collectionPageViewModel.requestCollectionData()
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_collection
    }

    override fun setupViewModel() {
        collectionPageViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CollectionPageViewModel::class.java)
    }

    override fun observeViewModelState() {
        collectionPageViewModel.getCollectionPageViewState().observe(this, Observer {
            onPageStateChanged(it)
        })
    }

    private fun onPageStateChanged(collectionPageViewState: CollectionPageViewState?) {
        collectionPageViewState?.let {
            when (it) {
                CollectionPageViewState.Loading -> handleLoadingView()
                CollectionPageViewState.Error -> handleErrorView()
                is CollectionPageViewState.Skills -> handleCollectionView(
                    it.skills,
                    CollectionType.SKILLS
                )
                is CollectionPageViewState.Experiences -> handleCollectionView(
                    it.experiences,
                    CollectionType.EXPERIENCES
                )
                is CollectionPageViewState.Projects -> handleCollectionView(
                    it.projects,
                    CollectionType.PROJECTS
                )
            }
        }
    }

    private fun handleCollectionView(
        list: List<CollectionItemUiModel>,
        collectionType: CollectionType
    ) {
        progress_bar.visibility = View.GONE
        collection_list.visibility = View.VISIBLE
        collection_list.adapter =
            collectionPageAdapterFactory.create(collectionType, viewHolderFactory)
                .also { collectionAdapter = it }
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
}
