package com.scallop.data.repository

import com.scallop.data.api.ItunesApi
import com.scallop.data.commons.Properties
import com.scallop.data.mappers.MusicDataEntityMapper
import com.scallop.domain.entities.AlbumEntity
import com.scallop.domain.entities.ArtistEntity
import com.scallop.domain.entities.MusicVideoEntity
import com.scallop.domain.entities.SongEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MusicRemoteImpl constructor(private val mApi: ItunesApi) : RemoteDataSource {

    private val mMapper = MusicDataEntityMapper()

    override suspend fun getArtistsByName(
        name: String,
        page: Int
    ): Flow<List<ArtistEntity>> = flow {
        val offset = page * Properties.ITEMS_PER_PAGE
        val result = mApi.getArtistsByName(name, offset)
        emit(mMapper.mapArtistsToEntity(result.results))
    }

    override suspend fun getAlbumsFromArtist(
        name: String,
        page: Int
    ): Flow<List<AlbumEntity>> = flow {
        val offset = page * Properties.ITEMS_PER_PAGE
        val result = mApi.getAlbumsFromArtist(name, offset)
        emit(mMapper.mapAlbumToEntity(result.results))
    }

    override suspend fun getSongsFromAlbum(
        name: String,
        albumId: Long,
        page: Int
    ): Flow<List<SongEntity>> = flow {
        val offset = page * Properties.ITEMS_PER_PAGE
        val result = mApi.getSongsFromAlbum(name, offset)

        val validResults = result.results.filter { it1 -> it1.collectionId == albumId }
        emit(mMapper.mapSongToEntity(validResults))
    }

    override suspend fun getMusicVideo(
        name: String,
        trackId: Long
    ): Flow<MusicVideoEntity?> = flow {
        val result = mApi.getMusicVideo(name)
        if (result.results.isNotEmpty()) {
            emit(mMapper.mapMusicVideoToEntity(result.results[0]))
        } else {
            emit(null)
        }
    }
}