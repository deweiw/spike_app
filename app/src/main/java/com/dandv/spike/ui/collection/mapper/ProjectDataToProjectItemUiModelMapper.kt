package com.dandv.spike.ui.collection.mapper

import com.dandv.consultant.domain.common.basemapper.BaseMapperToPresentation
import com.dandv.domain.profile.entity.collection.ProjectData
import com.dandv.spike.ui.collection.model.ProjectItemUiModel

class ProjectDataToProjectItemUiModelMapper :
    BaseMapperToPresentation<ProjectData, ProjectItemUiModel> {

    override fun mapToPresentation(toBeTransformed: ProjectData): ProjectItemUiModel {
        return with(toBeTransformed) {
            ProjectItemUiModel(title, time, summary)
        }
    }
}