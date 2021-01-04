package com.scallop.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.scallop.data.entitites.SongData

@Database(entities = [SongData::class], version = 1, exportSchema = false)
abstract class MusicDatabase : RoomDatabase() {
    abstract fun getMusicDao(): MusicDao
}