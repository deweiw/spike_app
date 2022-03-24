package com.dandv.spike.ui.collection.mapper

import com.dandv.domain.common.basemapper.BaseMapperToPresentation
import com.dandv.domain.profile.entity.collection.SkillData
import com.dandv.spike.ui.collection.model.SkillItemUiModel
import javax.inject.Inject

class SkillDataToSkillItemUiModelMapper @Inject constructor() :
    BaseMapperToPresentation<SkillData, SkillItemUiModel> {

    override fun mapToPresentation(toBeTransformed: SkillData): SkillItemUiModel {
        return SkillItemUiModel(toBeTransformed.name)
    }
}
