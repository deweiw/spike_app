package com.dandv.data.profile.datasource.local


import androidx.room.*
import com.dandv.data.profile.model.ProfileRoomDto

@Dao
interface ProfileDao {

    @Query("SELECT * FROM " + ProfileRoomDto.TABLE_NAME)
    fun getProfile(): ProfileRoomDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProfile(profileRoomDto: ProfileRoomDto)

    @Delete
    fun deleteProfile(profileRoomDto: ProfileRoomDto)
}
