package com.dandv.data.profile.model

import com.google.gson.annotations.SerializedName

data class ProfileDto(

    @field:SerializedName("summary")
    val summary: String? = null,

    @field:SerializedName("skills")
    val skills: List<String?>? = null,

    @field:SerializedName("experienceSummary")
    val experienceSummary: List<ExperienceSummaryItemDto?>? = null,

    @field:SerializedName("phone")
    val phone: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("imageUrl")
    val imageUrl: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("from")
    val from: String? = null,

    @field:SerializedName("to")
    val to: String? = null,

    @field:SerializedName("projectSummary")
    val projectSummary: List<ProjectSummaryItemDto?>? = null
)