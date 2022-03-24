package com.dandv.spike.ui.collection.mapper

import com.dandv.domain.common.basemapper.BaseMapperToPresentation
import com.dandv.domain.profile.entity.collection.ProjectData
import com.dandv.spike.ui.collection.model.ProjectItemUiModel
import javax.inject.Inject

class ProjectDataToProjectItemUiModelMapper
@Inject constructor() : BaseMapperToPresentation<ProjectData, ProjectItemUiModel> {

    override fun mapToPresentation(toBeTransformed: ProjectData): ProjectItemUiModel {
        return with(toBeTransformed) {
            ProjectItemUiModel(title, time, summary)
        }
    }
}
