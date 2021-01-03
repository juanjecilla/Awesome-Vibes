package com.scallop.data.repository

import com.scallop.domain.entities.*
import kotlinx.coroutines.flow.Flow

interface MusicDataStore {
    suspend fun getArtistsByName(name: String, page: Int): Flow<ItunesApiResponseEntity<ArtistEntity>>
    suspend fun getAlbumsFromArtist(name: String, page: Int): Flow<ItunesApiResponseEntity<AlbumEntity>>
    suspend fun getSongsFromAlbum(name: String, albumId: Long, page: Int): Flow<ItunesApiResponseEntity<SongEntity>>
    suspend fun getMusicVideo(name: String, trackId: Long): Flow<MusicVideoEntity>
}