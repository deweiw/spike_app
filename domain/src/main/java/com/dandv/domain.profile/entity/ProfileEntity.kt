package com.dandv.domain.profile.entity

sealed class ProfileEntity {
    object Error : ProfileEntity()
    object Empty : ProfileEntity()
    data class Data(val profileData: ProfileData) : ProfileEntity()
}