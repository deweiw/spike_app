package com.dandv.spike.ui.home.mapper

import com.dandv.consultant.domain.common.basemapper.BaseMapperToPresentation
import com.dandv.domain.profile.entity.ProfileData
import com.dandv.spike.ui.home.model.HomePageUiModel

class ProfileDataToHomePageUiModelMapper : BaseMapperToPresentation<ProfileData, HomePageUiModel> {

    override fun mapToPresentation(toBeTransformed: ProfileData): HomePageUiModel {
        return with(toBeTransformed) {
            HomePageUiModel(
                summary,
                phone,
                email,
                imageUrl,
                name,
                from,
                to,
                projects.isNotEmpty(),
                skills.isNotEmpty(),
                experience.isNotEmpty()
            )
        }
    }
}