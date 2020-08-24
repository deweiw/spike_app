package com.dandv.spike.ui.collection.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.dandv.domain.profile.entity.collection.CollectionType
import com.dandv.spike.ui.collection.mapper.CollectionItemUiModel
import com.dandv.spike.ui.collection.viewholder.CollectionItemViewHolder
import com.dandv.spike.ui.collection.viewholder.ViewHolderFactory

class CollectionPageAdapter(
    private val collectionType: CollectionType,
    private val viewHolderFactory: ViewHolderFactory
) : RecyclerView.Adapter<CollectionItemViewHolder<CollectionItemUiModel>>() {

    private val collectionUiModel = mutableListOf<CollectionItemUiModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CollectionItemViewHolder<CollectionItemUiModel> {
        return viewHolderFactory.create(collectionType, parent)
    }

    override fun getItemCount(): Int {
        return collectionUiModel.size
    }

    override fun onBindViewHolder(
        holder: CollectionItemViewHolder<CollectionItemUiModel>,
        position: Int
    ) {
        holder.onBind(collectionUiModel[position])
    }

    fun updateAdapter(collectionItems: List<CollectionItemUiModel>) {
        collectionUiModel.clear()
        collectionUiModel.addAll(collectionItems)
        super.notifyDataSetChanged()
    }
}