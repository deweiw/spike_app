package com.dandv.data.profile.datasource.local


import androidx.room.*
import com.dandv.data.profile.model.ProfileRoomDto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Query("SELECT * FROM " + ProfileRoomDto.TABLE_NAME)
    fun getProfile(): Flow<ProfileRoomDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProfile(profileRoomDto: ProfileRoomDto)

    @Delete
    fun deleteProfile(profileRoomDto: ProfileRoomDto)
}
