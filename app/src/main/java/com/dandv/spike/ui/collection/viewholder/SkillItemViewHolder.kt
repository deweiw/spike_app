package com.dandv.spike.ui.collection.viewholder

import android.view.View
import com.dandv.spike.ui.collection.model.SkillItemUiModel
import kotlinx.android.synthetic.main.item_layout_skill.view.*

class SkillItemViewHolder(itemView: View) : CollectionItemViewHolder<SkillItemUiModel>(itemView) {

    override fun onBind(skillItemUiModel: SkillItemUiModel) {
        itemView.skill_title.text = skillItemUiModel.name
    }
}