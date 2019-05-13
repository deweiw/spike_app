package com.dandv.spike.ui.collection.model

/**
 * Collection page state. UI based on the state to update the UI
 */
sealed class CollectionPageViewState {
    object Loading : CollectionPageViewState()
    object Error : CollectionPageViewState()
    data class Skills(val skills: List<SkillItemUiModel>) : CollectionPageViewState()
    data class Projects(val projects: List<ProjectItemUiModel>) : CollectionPageViewState()
    data class Experiences(val experiences: List<ExperienceItemUiModel>) : CollectionPageViewState()
}