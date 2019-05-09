package com.dandv.data.profile.model

import com.google.gson.annotations.SerializedName

data class ProjectSummaryItemDto(

    @field:SerializedName("summary")
    val summary: String? = null,

    @field:SerializedName("time")
    val time: String? = null,

    @field:SerializedName("title")
    val title: String? = null
)