package com.dandv.data.profile.datasource.remote.mapper.collection

import com.dandv.consultant.domain.common.basemapper.BaseMapperToDomain
import com.dandv.data.profile.model.ProjectDto
import com.dandv.domain.profile.entity.collection.ProjectData

class ProjectDtoToProjectDataMapper : BaseMapperToDomain<ProjectDto, ProjectData> {

    override fun mapToDomain(toBeTransformed: ProjectDto): ProjectData {
        return with(toBeTransformed) {
            ProjectData(title.orEmpty(), time.orEmpty(), summary.orEmpty())
        }
    }
}