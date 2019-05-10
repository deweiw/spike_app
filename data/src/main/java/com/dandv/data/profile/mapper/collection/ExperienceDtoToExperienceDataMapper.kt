package com.dandv.data.profile.mapper.collection

import com.dandv.consultant.domain.common.basemapper.BaseMapperToDomain
import com.dandv.data.profile.model.ExperienceDto
import com.dandv.domain.profile.entity.collection.ExperienceData
import javax.inject.Inject

class ExperienceDtoToExperienceDataMapper
@Inject constructor() : BaseMapperToDomain<ExperienceDto, ExperienceData> {

    override fun mapToDomain(toBeTransformed: ExperienceDto): ExperienceData {
        return with(toBeTransformed) {
            ExperienceData(company.orEmpty(), from.orEmpty(), to.orEmpty(), summary.orEmpty())
        }
    }
}