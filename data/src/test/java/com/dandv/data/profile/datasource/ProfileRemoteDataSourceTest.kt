package com.dandv.data.profile.datasource

import com.dandv.data.common.NetworkClient
import com.dandv.data.profile.mapper.ProfileDtoToProfileEntityMapper
import com.dandv.data.profile.mapper.collection.ExperienceDtoToExperienceDataMapper
import com.dandv.data.profile.mapper.collection.ProjectDtoToProjectDataMapper
import com.dandv.data.profile.mapper.collection.SkillDtoToSkillDataMapper
import com.dandv.data.profile.model.ProfileDto
import com.dandv.domain.profile.entity.ProfileEntity
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ProfileRemoteDataSourceTest {

    @Mock
    private lateinit var networkClient: NetworkClient
    @Mock
    private lateinit var profileDtoToProfileEntityMapper: ProfileDtoToProfileEntityMapper
    @Mock
    private lateinit var skillDtoToSkillDataMapper: SkillDtoToSkillDataMapper
    @Mock
    private lateinit var projectDtoToProjectDataMapper: ProjectDtoToProjectDataMapper
    @Mock
    private lateinit var experienceDtoToExperienceDataMapper: ExperienceDtoToExperienceDataMapper
    private lateinit var cut: ProfileRemoteDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        cut = ProfileRemoteDataSource(
            networkClient,
            profileDtoToProfileEntityMapper,
            skillDtoToSkillDataMapper,
            projectDtoToProjectDataMapper,
            experienceDtoToExperienceDataMapper
        )
    }

    @Test
    fun `given network request unsuccessful when getProfile then ProfileEntity Error returned`() {
        // given
        val call = mock<Call<ProfileDto>>()
        given(networkClient.getProfile()).willReturn(call)
        val response = mock<Response<ProfileDto>>()
        given(call.execute()).willReturn(response)
        given(response.isSuccessful).willReturn(false)

        // when
        val result = cut.getProfile()

        // then
        verify(networkClient).getProfile()
        verify(profileDtoToProfileEntityMapper, never()).mapToDomain(any())
        assertTrue(result is ProfileEntity.Error)
    }

    @Test
    fun `given network request error when getProfile then ProfileEntity Error returned`() {
        // given
        val call = mock<Call<ProfileDto>>()
        given(networkClient.getProfile()).willReturn(call)
        given(call.execute()).willThrow()

        // when
        val result = cut.getProfile()

        // then
        verify(profileDtoToProfileEntityMapper, never()).mapToDomain(any())
        assertTrue(result is ProfileEntity.Error)
    }

    @Test
    fun `given network request successful with empty body when getProfile then ProfileEntity Empty returned`() {
        // given
        val call = mock<Call<ProfileDto>>()
        given(networkClient.getProfile()).willReturn(call)
        val response = mock<Response<ProfileDto>>()
        given(call.execute()).willReturn(response)
        given(response.isSuccessful).willReturn(true)

        // when
        val result = cut.getProfile()

        // then
        verify(networkClient).getProfile()
        verify(profileDtoToProfileEntityMapper, never()).mapToDomain(any())
        assertTrue(result is ProfileEntity.Empty)
    }

    @Test
    fun `given valid network request when getProfile then ProfileEntity Empty returned`() {
        // given
        val call = mock<Call<ProfileDto>>()
        given(networkClient.getProfile()).willReturn(call)
        val response = mock<Response<ProfileDto>>()
        given(call.execute()).willReturn(response)
        given(response.isSuccessful).willReturn(true)
        val profileDto = mock<ProfileDto>()
        given(response.body()).willReturn(profileDto)
        val profileEntity = mock<ProfileEntity>()
        given(profileDtoToProfileEntityMapper.mapToDomain(profileDto)).willReturn(profileEntity)

        // when
        val result = cut.getProfile()

        // then
        verify(networkClient).getProfile()
        verify(profileDtoToProfileEntityMapper).mapToDomain(profileDto)
        assertEquals(profileEntity, result)
    }

    // TODO Can test other functions(getProjects, getSkills, getExperiences) with similar scenarios as the above
}