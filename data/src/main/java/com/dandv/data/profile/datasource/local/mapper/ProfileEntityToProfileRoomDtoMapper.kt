package com.dandv.data.profile.datasource.local.mapper

import com.dandv.consultant.domain.common.basemapper.BaseMapperToData
import com.dandv.data.profile.model.ProfileRoomDto
import com.dandv.domain.profile.entity.ProfileData
import com.google.gson.Gson

class ProfileEntityToProfileRoomDtoMapper constructor(
    private val gson: Gson
) : BaseMapperToData<ProfileData, ProfileRoomDto> {

    override fun mapToData(toBeTransformed: ProfileData): ProfileRoomDto {
        return with(toBeTransformed) {
            ProfileRoomDto(
                name,
                summary,
                email,
                imageUrl,
                from,
                to,
                phone,
                gson.toJson(skills),
                gson.toJson(projects),
                gson.toJson(experience)
            )
        }
    }
}