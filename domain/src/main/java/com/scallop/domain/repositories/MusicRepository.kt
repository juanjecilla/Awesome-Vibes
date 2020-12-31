package com.scallop.domain.repositories

import com.scallop.domain.entities.*
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

interface MusicRepository {

    suspend fun getArtistsByName(name: String, page: Int): Flow<ItunesApiResponseEntity<ArtistEntity>>
    suspend fun getAlbumsFromArtist(name: String, page: Int): Flow<ItunesApiResponseEntity<AlbumEntity>>
}