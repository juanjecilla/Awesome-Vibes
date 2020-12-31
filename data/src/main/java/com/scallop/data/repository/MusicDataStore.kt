package com.scallop.data.repository

import com.scallop.data.entitites.AlbumData
import com.scallop.data.entitites.ArtistData
import com.scallop.data.entitites.ItunesApiResponseData
import com.scallop.domain.entities.AlbumEntity
import com.scallop.domain.entities.ArtistEntity
import com.scallop.domain.entities.ItunesApiResponseEntity
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

interface MusicDataStore {
    suspend fun getArtistsByName(name: String, page: Int): Flow<ItunesApiResponseEntity<ArtistEntity>>
    suspend fun getAlbumsFromArtist(name: String, page: Int): Flow<ItunesApiResponseEntity<AlbumEntity>>
}