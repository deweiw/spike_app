package com.dandv.data.common

import com.dandv.data.profile.model.ProfileDto
import com.dandv.data.profile.model.SkillDto
import retrofit2.Call
import retrofit2.http.GET

interface NetworkClient {

    @GET("b0d974313ea9cd026ec368935f381eb6/raw/c2ce2c8bd58073e55848719a5dca2147f2130060/profile")
    fun getProfile(): Call<ProfileDto>

    @GET("23fe3036ce8749a4301bedc53bcc7d50/raw/4a6ebcf927e657e990a690a1baf57d7d23526344/skills")
    fun getSkills(): Call<List<SkillDto>>
}