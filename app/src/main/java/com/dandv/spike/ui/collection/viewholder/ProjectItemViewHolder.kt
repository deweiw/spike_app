package com.dandv.spike.ui.collection.viewholder

import android.view.View
import com.dandv.spike.ui.collection.model.ProjectItemUiModel
import kotlinx.android.synthetic.main.item_layout_project.view.*

class ProjectItemViewHolder(itemView: View) :
    CollectionItemViewHolder<ProjectItemUiModel>(itemView) {

    override fun onBind(itemUiModel: ProjectItemUiModel) {
        itemView.project_title.text = itemUiModel.title
        itemView.project_duration.text = itemUiModel.time
        itemView.project_summary.text = itemUiModel.summary
    }
}