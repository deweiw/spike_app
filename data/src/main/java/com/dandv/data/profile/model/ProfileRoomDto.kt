package com.dandv.data.profile.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dandv.data.profile.model.ProfileRoomDto.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class ProfileRoomDto (

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = COLUMN_NAME)
    var name: String,

    @ColumnInfo(name = COLUMN_SUMMARY)
    var summary: String?,

    @ColumnInfo(name = COLUMN_EMAIL)
    var email: String?,

    @ColumnInfo(name = COLUMN_IMAGE_URL)
    var imageUrl: String?,

    @ColumnInfo(name = COLUMN_FROM)
    var from: String?,

    @ColumnInfo(name = COLUMN_TO)
    var to: String?,

    @ColumnInfo(name = COLUMN_PHONE)
    var phone: String?,

    @ColumnInfo(name = COLUMN_SKILLS)
    var skills: String?,

    @ColumnInfo(name = COLUMN_PROJECTS)
    var projects: String?,

    @ColumnInfo(name = COLUMN_EXPERIENCE)
    var experience: String?
) {
    companion object {
        const val TABLE_NAME = "profile_table"
        const val COLUMN_NAME = "column_name"
        const val COLUMN_EMAIL = "column_email"
        const val COLUMN_SUMMARY = "column_summary"
        const val COLUMN_IMAGE_URL = "column_imageUrl"
        const val COLUMN_FROM = "column_from"
        const val COLUMN_TO = "column_to"
        const val COLUMN_PHONE = "column_phone"
        const val COLUMN_SKILLS = "column_skills"
        const val COLUMN_PROJECTS = "column_projects"
        const val COLUMN_EXPERIENCE = "column_experience"
    }
}
