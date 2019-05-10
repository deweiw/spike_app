package com.dandv.data.profile.model

import com.google.gson.annotations.SerializedName

data class ExperienceDto(

    @field:SerializedName("summary")
    val summary: String? = null,

    @field:SerializedName("company")
    val company: String? = null,

    @field:SerializedName("from")
    val from: String? = null,

    @field:SerializedName("to")
    val to: String? = null
)