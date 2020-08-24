package com.dandv.spike.ui.collection

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.dandv.domain.profile.entity.collection.CollectionEntity
import com.dandv.domain.profile.usecase.GetCollectionUseCase
import com.dandv.spike.common.BaseViewModel
import com.dandv.spike.common.CoroutineScopeFactory
import com.dandv.spike.ui.collection.mapper.ExperienceDataToExperienceItemUiModelMapper
import com.dandv.spike.ui.collection.mapper.ProjectDataToProjectItemUiModelMapper
import com.dandv.spike.ui.collection.mapper.SkillDataToSkillItemUiModelMapper
import com.dandv.spike.ui.collection.model.CollectionPageViewState
import kotlinx.coroutines.launch

/**
 * Request collection data to be displayed on the UI.
 *
 * Collection data can be three types: Projects, Skills and Experiences.
 * View Model takes the responsibility to convert the data from the domain entity to the format which needed
 * by the UI (UiModels).
 */
class CollectionPageViewModel
constructor(
    private val getCollectionUseCase: GetCollectionUseCase,
    private val skillDataToSkillItemUiModelMapper: SkillDataToSkillItemUiModelMapper,
    private val projectDataToProjectItemUiModelMapper: ProjectDataToProjectItemUiModelMapper,
    private val experienceDataToExperienceItemUiModelMapper: ExperienceDataToExperienceItemUiModelMapper,
    coroutineScopeFactory: CoroutineScopeFactory
) : BaseViewModel(coroutineScopeFactory) {

    private val collectionPageViewState = MutableLiveData<CollectionPageViewState>()

    fun getCollectionPageViewState(): LiveData<CollectionPageViewState> = collectionPageViewState

    fun requestCollectionData() {
        collectionPageViewState.postValue(CollectionPageViewState.Loading)
        coroutineScope.launch {
            val result = getCollectionUseCase.buildUseCase()
            when (result) {
                is CollectionEntity.SkillCollection -> collectionPageViewState.postValue(
                    CollectionPageViewState.Skills(result.skills.map {
                        skillDataToSkillItemUiModelMapper.mapToPresentation(it)
                    })
                )
                is CollectionEntity.ProjectCollection -> collectionPageViewState.postValue(
                    CollectionPageViewState.Projects(result.projects.map {
                        projectDataToProjectItemUiModelMapper.mapToPresentation(it)
                    })
                )
                is CollectionEntity.ExperienceCollection -> collectionPageViewState.postValue(
                    CollectionPageViewState.Experiences(result.experiences.map {
                        experienceDataToExperienceItemUiModelMapper.mapToPresentation(it)
                    })
                )
                CollectionEntity.Empty,
                CollectionEntity.Error -> collectionPageViewState.postValue(CollectionPageViewState.Error)
            }
        }
    }
}