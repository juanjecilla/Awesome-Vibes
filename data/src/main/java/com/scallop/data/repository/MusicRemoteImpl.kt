package com.scallop.data.repository

import android.util.Log
import com.scallop.data.api.ItunesApi
import com.scallop.data.commons.Properties
import com.scallop.data.mappers.MusicDataEntityMapper
import com.scallop.domain.entities.AlbumEntity
import com.scallop.domain.entities.ArtistEntity
import com.scallop.domain.entities.SongEntity
import com.scallop.domain.entities.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MusicRemoteImpl constructor(private val mApi: ItunesApi) : MusicDataStore {

    private val mMapper = MusicDataEntityMapper()

    override suspend fun getArtistsByName(
        name: String,
        page: Int
    ): Flow<List<ArtistEntity>> {
        val offset = page * Properties.ITEMS_PER_PAGE
        val result = mApi.getArtistsByName(name, offset)
        return flow {
            result.body()?.let { emit(mMapper.mapArtistsToEntity(it.results)) }
        }
    }

    override suspend fun getAlbumsFromArtist(
        name: String,
        page: Int
    ): Flow<List<AlbumEntity>> {
        val offset = page * Properties.ITEMS_PER_PAGE
        val result = mApi.getAlbumsFromArtist(name, offset)
        return flow {
            result.body()?.let { emit(mMapper.mapToEntity(it.results)) }
        }
    }

    override suspend fun getSongsFromAlbum(
        name: String,
        albumId: Long,
        page: Int
    ): Flow<List<SongEntity>> {
        val offset = page * Properties.ITEMS_PER_PAGE
        val result = mApi.getSongsFromAlbum(name, offset)

        return flow {
            result.body()?.let {
                val validResults = it.results.filter { it1 -> it1.collectionId == albumId }
                emit(mMapper.mapSongToEntity(validResults))
            }
        }
    }

    override suspend fun getMusicVideo(
        name: String,
        trackId: Long
    ): Flow<MusicVideoEntity> {
        val result = mApi.getMusicVideo(name)
        return flow {
            result.body()?.let {
                emit(mMapper.mapMusicVideoToEntity(it.results[0]))
            }
        }
    }
}