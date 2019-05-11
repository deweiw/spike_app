package com.dandv.data.profile.repository

import com.dandv.data.profile.datasource.ProfileRemoteDataSource
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ProfileRepositoryImplTest {

    @Mock
    private lateinit var profileRemoteDataSource: ProfileRemoteDataSource
    private lateinit var cut: ProfileRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        cut = ProfileRepositoryImpl(profileRemoteDataSource)
    }

    @Test
    fun `when getProfile called then remote data source getProfile called`() {
        runBlocking {
            // given

            // when
            cut.getProfile()

            // then
            verify(profileRemoteDataSource).getProfile()
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