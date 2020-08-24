package com.dandv.spike.ui.collection.adapter

import com.dandv.domain.profile.entity.collection.CollectionType
import com.dandv.spike.ui.collection.viewholder.ViewHolderFactory

class CollectionPageAdapterFactory {

    fun create(
        collectionType: CollectionType,
        viewHolderFactory: ViewHolderFactory
    ): CollectionPageAdapter {
        return CollectionPageAdapter(collectionType, viewHolderFactory)
    }
}