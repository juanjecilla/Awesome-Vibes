package com.scallop.data.db

import androidx.room.*
import com.scallop.data.entitites.SongData
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicDao {

    @Query("Select * from songs")
    fun getSavedSongs(): Flow<List<SongData>>

    @Query("Select * from songs where collectionId = :albumId")
    fun getSavedSongs(albumId: Long): Flow<List<SongData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSong(songData: SongData)

    @Delete
    fun deleteSong(songData: SongData)

    @Query("DELETE FROM songs")
    fun clearSongs()

    @Query("Select * from songs where trackId = :id")
    fun getSavedSong(id: Int): SongData?
}