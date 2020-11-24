package com.esiea.project.data.local.models

import androidx.room.Database
import androidx.room.RoomDatabase
import com.esiea.project.data.local.DatabaseDao

@Database(entities = arrayOf(
    UserLocal::class
), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao
}