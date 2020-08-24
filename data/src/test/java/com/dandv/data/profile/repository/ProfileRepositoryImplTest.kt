package com.dandv.data.profile.repository

import com.dandv.data.profile.datasource.local.ProfileLocalDataSource
import com.dandv.data.profile.datasource.remote.ProfileRemoteDataSource
import com.dandv.domain.profile.entity.ProfileEntity
import com.nhaarman.mockito_kotlin.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ProfileRepositoryImplTest {

    @Mock
    private lateinit var profileLocalDataSource: ProfileLocalDataSource

    @Mock
    private lateinit var profileRemoteDataSource: ProfileRemoteDataSource
    private lateinit var cut: ProfileRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        cut = ProfileRepositoryImpl(profileLocalDataSource, profileRemoteDataSource)
    }

    @Test
    fun `given empty local profile when getProfile called then remote getProfile called and save profile to local database`() {
        runBlocking {
            // given
            given(profileLocalDataSource.getProfile()).willReturn(ProfileEntity.Error)
            val profileEntity = mock<ProfileEntity.Data>()
            given(profileRemoteDataSource.getProfile()).willReturn(profileEntity)

            // when
            cut.getProfile()

            // then
            verify(profileLocalDataSource).getProfile()
            verify(profileRemoteDataSource).getProfile()
            verify(profileLocalDataSource).saveProfile(profileEntity)
        }
    }

    @Test
    fun `given valid local profile when getProfile called then no remote getProfile called`() {
        runBlocking {
            // given
            val data = ProfileEntity.Data(mock())
            given(profileLocalDataSource.getProfile()).willReturn(data)

            // when
            cut.getProfile()

            // then
            verify(profileLocalDataSource).getProfile()
            verify(profileRemoteDataSource, never()).getProfile()
            verify(profileLocalDataSource, never()).saveProfile(any())
        }
    }

    @Test
    fun `when getSkills called then remote data source getSkills called`() {
        runBlocking {
            // given

            // when
            cut.getSkills()

            // then
            verify(profileRemoteDataSource).getSkills()
        }
    }

    @Test
    fun `when getExperiences called then remote data source getExperiences called`() {
        runBlocking {
            // given

            // when
            cut.getExperiences()

            // then
            verify(profileRemoteDataSource).getExperiences()
        }
    }

    @Test
    fun `when getProjects called then remote data source getProjects called`() {
        runBlocking {
            // given

            // when
            cut.getProjects()

            // then
            verify(profileRemoteDataSource).getProjects()
        }
    }
}