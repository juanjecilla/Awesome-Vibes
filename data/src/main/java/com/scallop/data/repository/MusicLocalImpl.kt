package com.scallop.data.repository

import com.scallop.data.mappers.MusicDataEntityMapper
import com.scallop.data.mappers.MusicEntityDataMapper
import com.scallop.domain.entities.*
import kotlinx.coroutines.flow.Flow

class MusicLocalImpl(
    private val entityToDataMapper: MusicEntityDataMapper,
    private val dataToEntityMapper: MusicDataEntityMapper
) : MusicDataStore {


    override suspend fun getArtistsByName(name: String, page: Int): Flow<ItunesApiResponseEntity<ArtistEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAlbumsFromArtist(name: String, page: Int): Flow<ItunesApiResponseEntity<AlbumEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSongsFromAlbum(
        name: String,
        albumId: Long,
        page: Int
    ): Flow<ItunesApiResponseEntity<SongEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMusicVideo(name: String, trackId: Long): Flow<MusicVideoEntity> {
        TODO("Not yet implemented")
    }
}