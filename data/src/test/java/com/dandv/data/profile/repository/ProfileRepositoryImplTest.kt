package com.dandv.data.profile.repository

import com.dandv.data.profile.datasource.local.ProfileLocalDataSource
import com.dandv.data.profile.datasource.remote.ProfileRemoteDataSource
import com.dandv.domain.common.di.TestDispatcherProvider
import com.dandv.domain.profile.entity.ProfileEntity
import com.dandv.domain.profile.entity.collection.CollectionEntity
import com.nhaarman.mockito_kotlin.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class ProfileRepositoryImplTest {

    @Mock
    private lateinit var profileLocalDataSource: ProfileLocalDataSource

    @Mock
    private lateinit var profileRemoteDataSource: ProfileRemoteDataSource
    private lateinit var cut: ProfileRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        cut = ProfileRepositoryImpl(
            profileLocalDataSource,
            profileRemoteDataSource,
            TestDispatcherProvider()
        )
    }

    @Test
    fun `given empty local profile when getProfile called then remote getProfile called and save profile to local database`() {
        runBlocking {
            // given
            given(profileLocalDataSource.getProfile()).willReturn(flowOf(ProfileEntity.Error))
            val profileEntity = mock<ProfileEntity.Data>()
            given(profileRemoteDataSource.getProfile()).willReturn(flowOf(profileEntity))

            // when
            cut.getProfile().collect {
                assertEquals(profileEntity, it)
            }

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
            given(profileLocalDataSource.getProfile()).willReturn(flowOf(data))

            // when
            cut.getProfile().collect()

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
            val values = mutableListOf<CollectionEntity>()

            // when
            cut.getSkills().collect { values.add(it) }

            // then
            verify(profileRemoteDataSource).getSkills()
        }
    }

    @Test
    fun `when getExperiences called then remote data source getExperiences called`() {
        runBlocking {
            // given

            // when
            cut.getExperiences().collect()

            // then
            verify(profileRemoteDataSource).getExperiences()
        }
    }

    @Test
    fun `when getProjects called then remote data source getProjects called`() {
        runBlocking {
            // given

            // when
            cut.getProjects().collect()

            // then
            verify(profileRemoteDataSource).getProjects()
        }
    }
}
