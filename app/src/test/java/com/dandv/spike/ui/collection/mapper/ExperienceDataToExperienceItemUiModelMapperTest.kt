package com.dandv.spike.ui.collection.mapper

import com.dandv.domain.profile.entity.collection.ExperienceData
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ExperienceDataToExperienceItemUiModelMapperTest {

    @Mock
    private lateinit var stringBuilder: StringBuilder
    private lateinit var cut: ExperienceDataToExperienceItemUiModelMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        cut = ExperienceDataToExperienceItemUiModelMapper(stringBuilder)
    }

    @Test
    fun `given toBeTransformed data when mapToPresentation called then get expect result`() {
        // given
        val expectDuration = "result"
        val expectCompany = "abc"
        val expectSummary = "summary"
        given(stringBuilder.append(any<String>())).willReturn(stringBuilder)
        given(stringBuilder.toString()).willReturn(expectDuration)

        // when
        val result =
            cut.mapToPresentation(ExperienceData(expectCompany, "from", "to", expectSummary))

        // then
        assertEquals(expectCompany, result.company)
        assertEquals(expectDuration, result.duration)
        assertEquals(expectSummary, result.summary)
    }
}