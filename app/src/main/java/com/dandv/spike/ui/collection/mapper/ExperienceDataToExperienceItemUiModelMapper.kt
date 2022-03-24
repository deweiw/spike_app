package com.dandv.spike.ui.collection.mapper

import com.dandv.domain.common.basemapper.BaseMapperToPresentation
import com.dandv.domain.profile.entity.collection.ExperienceData
import com.dandv.spike.ui.collection.model.ExperienceItemUiModel

class ExperienceDataToExperienceItemUiModelMapper(private val stringBuilder: StringBuilder) :
    BaseMapperToPresentation<ExperienceData, ExperienceItemUiModel> {

    override fun mapToPresentation(toBeTransformed: ExperienceData): ExperienceItemUiModel {
        stringBuilder.clear()
        return with(toBeTransformed) {
            ExperienceItemUiModel(company, stringBuilder.append(from).append(" - ").append(to).toString(), summary)
        }
    }
}
