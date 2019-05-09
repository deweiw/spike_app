package com.dandv.domain.profile.repository

import com.dandv.domain.profile.entity.ProfileEntity

interface ProfileRepository {

    suspend fun getProfile(): ProfileEntity
}