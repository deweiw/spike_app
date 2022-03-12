package com.dandv.spike.ui.collection.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dandv.spike.ui.collection.mapper.CollectionItemUiModel

abstract class CollectionItemViewHolder<T : CollectionItemUiModel>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBind(itemUiModel: T)
}
