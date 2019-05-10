package com.dandv.spike.ui.collection

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.dandv.domain.profile.entity.collection.CollectionEntity
import com.dandv.domain.profile.usecase.GetCollectionUseCase
import com.dandv.spike.common.BaseViewModel
import com.dandv.spike.common.CoroutineScopeFactory
import com.dandv.spike.ui.collection.mapper.SkillDataToSkillItemUiModelMapper
import com.dandv.spike.ui.collection.model.CollectionPageViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

class CollectionPageViewModel
@Inject constructor(
    private val getCollectionUseCase: GetCollectionUseCase,
    private val skillDataToSkillItemUiModelMapper: SkillDataToSkillItemUiModelMapper,
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
                CollectionEntity.Empty,
                CollectionEntity.Error -> collectionPageViewState.postValue(CollectionPageViewState.Error)
            }
        }
    }
}