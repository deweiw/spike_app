package com.dandv.spike.ui.collection

import com.dandv.domain.profile.entity.collection.CollectionEntity
import com.dandv.domain.profile.usecase.GetCollectionUseCase
import com.dandv.spike.common.BaseViewModel
import com.dandv.spike.common.CoroutineScopeFactory
import com.dandv.spike.ui.collection.mapper.ExperienceDataToExperienceItemUiModelMapper
import com.dandv.spike.ui.collection.mapper.ProjectDataToProjectItemUiModelMapper
import com.dandv.spike.ui.collection.mapper.SkillDataToSkillItemUiModelMapper
import com.dandv.spike.ui.collection.model.CollectionPageViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Request collection data to be displayed on the UI.
 *
 * Collection data can be three types: Projects, Skills and Experiences.
 * View Model takes the responsibility to convert the data from the domain entity to the format which needed
 * by the UI (UiModels).
 */
@HiltViewModel
class CollectionPageViewModel
@Inject constructor(
    private val getCollectionUseCase: GetCollectionUseCase,
    private val skillDataToSkillItemUiModelMapper: SkillDataToSkillItemUiModelMapper,
    private val projectDataToProjectItemUiModelMapper: ProjectDataToProjectItemUiModelMapper,
    private val experienceDataToExperienceItemUiModelMapper: ExperienceDataToExperienceItemUiModelMapper,
    coroutineScopeFactory: CoroutineScopeFactory
) : BaseViewModel(coroutineScopeFactory) {

    private val _collectionPageViewState =
        MutableStateFlow<CollectionPageViewState>(CollectionPageViewState.Loading)
    val collectionPageViewState: Flow<CollectionPageViewState> = _collectionPageViewState

    fun requestCollectionData() {
        coroutineScope.launch {
            getCollectionUseCase.buildUseCase().collect { collectionEntity ->
                when (collectionEntity) {
                    is CollectionEntity.SkillCollection -> _collectionPageViewState.update {
                        CollectionPageViewState.Skills(collectionEntity.skills.map {
                            skillDataToSkillItemUiModelMapper.mapToPresentation(it)
                        })
                    }
                    is CollectionEntity.ProjectCollection -> _collectionPageViewState.update {
                        CollectionPageViewState.Projects(collectionEntity.projects.map {
                            projectDataToProjectItemUiModelMapper.mapToPresentation(it)
                        })
                    }
                    is CollectionEntity.ExperienceCollection -> _collectionPageViewState.update {
                        CollectionPageViewState.Experiences(collectionEntity.experiences.map {
                            experienceDataToExperienceItemUiModelMapper.mapToPresentation(it)
                        })
                    }
                    CollectionEntity.Empty,
                    CollectionEntity.Error -> _collectionPageViewState.update { CollectionPageViewState.Error }
                }
            }
        }
    }
}
