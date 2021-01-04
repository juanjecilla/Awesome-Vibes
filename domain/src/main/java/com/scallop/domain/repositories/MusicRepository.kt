package com.scallop.domain.repositories

import com.scallop.domain.entities.AlbumEntity
import com.scallop.domain.entities.ArtistEntity
import com.scallop.domain.entities.MusicVideoEntity
import com.scallop.domain.entities.SongEntity
import kotlinx.coroutines.flow.Flow

interface MusicRepository {

    suspend fun getArtistsByName(
        name: String,
        page: Int
    ): Flow<List<ArtistEntity>>

    suspend fun getAlbumsFromArtist(
        name: String,
        page: Int
    ): Flow<List<AlbumEntity>>

    suspend fun getSongsFromAlbum(
        name: String,
        albumId: Long,
        page: Int
    ): Flow<List<SongEntity>>

    suspend fun saveSong(song: SongEntity)

    suspend fun deleteSong(song: SongEntity)

    suspend fun getMusicVideo(
        name: String,
        trackId: Long
    ): Flow<MusicVideoEntity>
}