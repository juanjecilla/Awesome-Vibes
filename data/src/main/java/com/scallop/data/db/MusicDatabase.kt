package com.scallop.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.scallop.data.entitites.MovieDetailData
import com.scallop.data.entitites.MovieItemData

@Database(entities = [MovieItemData::class, MovieDetailData::class], version = 1)
abstract class MusicDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MusicDao
}