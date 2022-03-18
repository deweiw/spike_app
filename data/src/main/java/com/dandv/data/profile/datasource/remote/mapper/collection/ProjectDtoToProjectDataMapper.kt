package com.dandv.data.profile.datasource.remote.mapper.collection

import com.dandv.data.profile.model.ProjectDto
import com.dandv.domain.common.basemapper.BaseMapperToDomain
import com.dandv.domain.profile.entity.collection.ProjectData
import javax.inject.Inject

class ProjectDtoToProjectDataMapper
@Inject constructor() : BaseMapperToDomain<ProjectDto, ProjectData> {

    override fun mapToDomain(toBeTransformed: ProjectDto): ProjectData {
        return with(toBeTransformed) {
            ProjectData(title.orEmpty(), time.orEmpty(), summary.orEmpty())
        }
    }
}
