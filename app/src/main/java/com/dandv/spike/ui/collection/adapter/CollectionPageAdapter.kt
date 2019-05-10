package com.dandv.spike.ui.collection.adapter

import android.app.Application
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dandv.spike.R
import com.dandv.spike.ui.collection.model.SkillItemUiModel
import com.dandv.spike.ui.collection.viewholder.SkillItemViewHolder
import javax.inject.Inject

class CollectionPageAdapter
@Inject constructor(private val application: Application) : RecyclerView.Adapter<SkillItemViewHolder>() {

    private val collectionUiModel = mutableListOf<SkillItemUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillItemViewHolder {
        return SkillItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout_skill,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return collectionUiModel.size
    }

    override fun onBindViewHolder(holder: SkillItemViewHolder, position: Int) {
        holder.onBind(collectionUiModel[position], application)
    }

    fun updateAdapter(collectionItems: List<SkillItemUiModel>) {
        collectionUiModel.clear()
        collectionUiModel.addAll(collectionItems)
        super.notifyDataSetChanged()
    }
}