package com.dandv.spike.ui.collection.viewholder

import android.view.View
import com.dandv.spike.ui.collection.model.ExperienceItemUiModel
import kotlinx.android.synthetic.main.item_layout_experience.view.*

class ExperienceItemViewHolder(itemView: View) :
    CollectionItemViewHolder<ExperienceItemUiModel>(itemView) {

    override fun onBind(itemUiModel: ExperienceItemUiModel) {
        itemView.experience_company.text = itemUiModel.company
        itemView.experience_duration.text = itemUiModel.duration
        itemView.experience_summary.text = itemUiModel.summary
    }
}