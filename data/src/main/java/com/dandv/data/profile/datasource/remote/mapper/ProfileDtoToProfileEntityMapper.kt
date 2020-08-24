package com.dandv.data.profile.datasource.remote.mapper

import com.dandv.consultant.domain.common.basemapper.BaseMapperToDomain
import com.dandv.data.profile.model.ProfileDto
import com.dandv.domain.profile.entity.*

class ProfileDtoToProfileEntityMapper : BaseMapperToDomain<ProfileDto, ProfileEntity> {

    override fun mapToDomain(toBeTransformed: ProfileDto): ProfileEntity {
        return with(toBeTransformed) {
            ProfileEntity.Data(
                ProfileData(
                    summary.orEmpty(),
                    phone.orEmpty(),
                    email.orEmpty(),
                    imageUrl.orEmpty(),
                    name.orEmpty(),
                    from.orEmpty(),
                    to.orEmpty(),
                    projectSummary?.map {
                        ProjectSummary(
                            it?.title.orEmpty(),
                            it?.time.orEmpty(),
                            it?.summary.orEmpty()
                        )
                    } ?: emptyList(),
                    skills?.map { SkillSummary(it.orEmpty()) } ?: emptyList(),
                    experienceSummary?.map {
                        ExperienceSummary(
                            it?.company.orEmpty(),
                            it?.from.orEmpty(),
                            it?.to.orEmpty(),
                            it?.summary.orEmpty()
                        )
                    } ?: emptyList()
                )
            )
        }
    }
}