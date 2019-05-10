package com.dandv.spike.ui.collection.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dandv.domain.profile.entity.collection.CollectionType
import com.dandv.spike.R
import com.dandv.spike.ui.collection.mapper.CollectionItemUiModel
import javax.inject.Inject

class ViewHolderFactory @Inject constructor() {

    fun create(collectionType: CollectionType, parent: ViewGroup): CollectionItemViewHolder<CollectionItemUiModel> {
        return when (collectionType) {
            CollectionType.SKILLS -> SkillItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_layout_skill,
                    parent,
                    false
                )
            ) as CollectionItemViewHolder<CollectionItemUiModel>
            CollectionType.EXPERIENCES -> ExperienceItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_layout_experience,
                    parent,
                    false
                )
            ) as CollectionItemViewHolder<CollectionItemUiModel>
            CollectionType.PROJECTS -> ProjectItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_layout_project,
                    parent,
                    false
                )
            ) as CollectionItemViewHolder<CollectionItemUiModel>
        }
    }
}