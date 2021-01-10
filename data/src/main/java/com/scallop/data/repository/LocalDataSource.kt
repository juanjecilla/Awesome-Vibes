package com.scallop.data.repository

import com.scallop.domain.entities.SongEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun getSongsFromAlbum(
        albumId: Long,
    ): Flow<List<SongEntity>>

    suspend fun saveSong(song: SongEntity)
    suspend fun deleteSong(song: SongEntity)
    suspend fun getSong(songId: Int): SongEntity?
}