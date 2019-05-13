package com.dandv.data.common

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.dandv.data.profile.datasource.local.ProfileDao
import com.dandv.data.profile.model.ProfileRoomDto

@Database(entities = [ProfileRoomDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao
}