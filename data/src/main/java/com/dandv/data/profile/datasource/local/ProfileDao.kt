package com.dandv.data.profile.datasource.local



import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Delete
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