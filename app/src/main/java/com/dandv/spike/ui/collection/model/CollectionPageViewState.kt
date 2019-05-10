package com.dandv.spike.ui.collection.model

sealed class CollectionPageViewState {
    object Loading : CollectionPageViewState()
    object Error : CollectionPageViewState()
    data class Skills(val skills: List<SkillItemUiModel>) : CollectionPageViewState()
}