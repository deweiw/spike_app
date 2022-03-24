package com.dandv.data.profile.datasource.local

import com.dandv.data.profile.datasource.local.mapper.ProfileEntityToProfileRoomDtoMapper
import com.dandv.data.profile.datasource.local.mapper.ProfileRoomDtoToProfileEntityMapper
import com.dandv.data.profile.model.ProfileRoomDto
import com.dandv.domain.common.di.TestDispatcherProvider
import com.dandv.domain.profile.entity.ProfileData
import com.dandv.domain.profile.entity.ProfileEntity
import com.nhaarman.mockito_kotlin.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertTrue

class ProfileLocalDataSourceTest {

    @Mock
    private lateinit var profileDao: ProfileDao

    @Mock
    private lateinit var profileRoomDtoToProfileEntityMapper: ProfileRoomDtoToProfileEntityMapper

    @Mock
    private lateinit var profileEntityToProfileRoomDtoMapper: ProfileEntityToProfileRoomDtoMapper
    private lateinit var cut: ProfileLocalDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        cut =
            ProfileLocalDataSource(
                profileDao,
                profileRoomDtoToProfileEntityMapper,
                profileEntityToProfileRoomDtoMapper,
                TestDispatcherProvider()
            )
    }

    @Test
    fun `given valid local profilewhen getProfile called then return ProfileEntity Data`() {
        // given
        val profileRoomDto = mock<ProfileRoomDto>()
        given(profileDao.getProfile()).willReturn(flowOf(profileRoomDto))
        val profileEntity = mock<ProfileEntity.Data>()
        given(profileRoomDtoToProfileEntityMapper.mapToDomain(profileRoomDto)).willReturn(
            profileEntity
        )

        // when
        runBlocking {
            cut.getProfile().collect {
                assertTrue(it is ProfileEntity.Data)
            }
        }
    }

    @Test
    fun `given invalid local profile when getProfile called then return ProfileEntity Error`() {
        // given
        given(profileDao.getProfile()).willReturn(flow {
            Exception()
        })

        // when
        runBlocking {
            cut.getProfile().collect {
                assertTrue(it is ProfileEntity.Error)
            }
        }
        // then
        verify(profileRoomDtoToProfileEntityMapper, never()).mapToDomain(any())
    }

    @Test
    fun `given valid ProfileEntity when saveProfile called then saved to local database`() {
        // given
        runBlocking {
            val profileData = mock<ProfileData>()
            val profileEntity = ProfileEntity.Data(profileData)
            val profileRoomDto = mock<ProfileRoomDto>()
            given(profileEntityToProfileRoomDtoMapper.mapToData(profileData)).willReturn(
                profileRoomDto
            )

            // when
            cut.saveProfile(profileEntity)

            // then
            verify(profileEntityToProfileRoomDtoMapper).mapToData(profileData)
            verify(profileDao).saveProfile(profileRoomDto)
        }
    }

    @Test
    fun `given invalid ProfileEntity when saveProfile called nothing saved`() {
        // given
        runBlocking {
            val profileEntity = ProfileEntity.Error

            // when
            cut.saveProfile(profileEntity)

            // then
            verify(profileEntityToProfileRoomDtoMapper, never()).mapToData(any())
            verify(profileDao, never()).saveProfile(any())
        }
    }
}
