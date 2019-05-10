package com.dandv.spike.ui.collection.viewholder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.dandv.spike.ui.collection.model.SkillItemUiModel
import kotlinx.android.synthetic.main.item_layout_skill.view.*

class SkillItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun onBind(skillItemUiModel: SkillItemUiModel, context: Context) {
        itemView.skill_title.text = skillItemUiModel.name
    }
}