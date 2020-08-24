package com.dandv.data.profile.datasource.local.mapper

import com.dandv.consultant.domain.common.basemapper.BaseMapperToDomain
import com.dandv.data.profile.model.ProfileRoomDto
import com.dandv.domain.profile.entity.*
import com.google.gson.Gson

class ProfileRoomDtoToProfileEntityMapper constructor(
    private val gson: Gson
) : BaseMapperToDomain<ProfileRoomDto, ProfileEntity> {

    override fun mapToDomain(toBeTransformed: ProfileRoomDto): ProfileEntity {
        return with(toBeTransformed) {
            ProfileEntity.Data(
                ProfileData(
                    summary.orEmpty(),
                    phone.orEmpty(),
                    email.orEmpty(),
                    imageUrl.orEmpty(),
                    name,
                    from.orEmpty(),
                    to.orEmpty(),
                    gson.fromJson(projects, Array<ProjectSummary>::class.java).asList(),
                    gson.fromJson(skills, Array<SkillSummary>::class.java).asList(),
                    gson.fromJson(experience, Array<ExperienceSummary>::class.java).asList()
                )
            )
        }
    }
}