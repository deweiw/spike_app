package com.dandv.data.common

import com.dandv.data.profile.model.ProfileDto
import retrofit2.Call
import retrofit2.http.GET

interface NetworkClient {

    @GET("b0d974313ea9cd026ec368935f381eb6/raw/c2ce2c8bd58073e55848719a5dca2147f2130060/profile")
    fun getProfile(): Call<ProfileDto>
}