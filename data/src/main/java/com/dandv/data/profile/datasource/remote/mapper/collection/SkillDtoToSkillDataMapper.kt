package com.dandv.data.profile.datasource.remote.mapper.collection

import com.dandv.consultant.domain.common.basemapper.BaseMapperToDomain
import com.dandv.data.profile.model.SkillDto
import com.dandv.domain.profile.entity.collection.SkillData

class SkillDtoToSkillDataMapper : BaseMapperToDomain<SkillDto, SkillData> {

    override fun mapToDomain(toBeTransformed: SkillDto): SkillData {
        return SkillData(toBeTransformed.skill.orEmpty())
    }
}