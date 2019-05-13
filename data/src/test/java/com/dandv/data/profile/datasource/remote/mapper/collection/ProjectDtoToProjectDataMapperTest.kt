package com.dandv.data.profile.datasource.remote.mapper.collection

import com.dandv.data.profile.model.ProjectDto
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ProjectDtoToProjectDataMapperTest {

    private lateinit var cut: ProjectDtoToProjectDataMapper

    @Before
    fun setUp() {
        cut = ProjectDtoToProjectDataMapper()
    }

    @Test
    fun `given ProjectDto object when mapToDomain then return ProjectData object`() {
        // given
        val expectSummary = "summary"
        val expectTime = "time"
        val expectTitle = "title"
        val projectDto = ProjectDto(expectSummary, expectTime, expectTitle)

        // when
        val result = cut.mapToDomain(projectDto)

        // then
        assertEquals(expectSummary, result.summary)
        assertEquals(expectTime, result.time)
        assertEquals(expectTitle, result.title)
    }
}