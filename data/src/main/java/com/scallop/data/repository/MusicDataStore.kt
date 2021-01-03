package com.scallop.data.repository

import com.scallop.domain.entities.AlbumEntity
import com.scallop.domain.entities.ArtistEntity
import com.scallop.domain.entities.MusicVideoEntity
import com.scallop.domain.entities.SongEntity
import kotlinx.coroutines.flow.Flow

interface MusicDataStore {
    suspend fun getArtistsByName(name: String, page: Int = 0): Flow<List<ArtistEntity>>
    suspend fun getAlbumsFromArtist(name: String, page: Int = 0): Flow<List<AlbumEntity>>
    suspend fun getSongsFromAlbum(
        name: String, albumId: Long, page: Int = 0
    ): Flow<List<SongEntity>>
    suspend fun getMusicVideo(name: String, trackId: Long): Flow<MusicVideoEntity>
}