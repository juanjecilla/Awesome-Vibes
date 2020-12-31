package com.scallop.data.repository

import com.scallop.domain.entities.AlbumEntity
import com.scallop.domain.entities.ArtistEntity
import com.scallop.domain.entities.ItunesApiResponseEntity
import com.scallop.domain.entities.SongEntity
import kotlinx.coroutines.flow.Flow

interface MusicDataStore {
    suspend fun getArtistsByName(name: String, page: Int): Flow<ItunesApiResponseEntity<ArtistEntity>>
    suspend fun getAlbumsFromArtist(name: String, page: Int): Flow<ItunesApiResponseEntity<AlbumEntity>>
    suspend fun getSongsFromAlbum(name: String, albumId: Long, page: Int): Flow<ItunesApiResponseEntity<SongEntity>>
}