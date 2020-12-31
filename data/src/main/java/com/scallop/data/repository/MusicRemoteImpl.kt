package com.scallop.data.repository

import com.scallop.data.api.ItunesApi
import com.scallop.data.commons.Properties
import com.scallop.data.mappers.MusicDataEntityMapper
import com.scallop.domain.entities.AlbumEntity
import com.scallop.domain.entities.ArtistEntity
import com.scallop.domain.entities.ItunesApiResponseEntity
import com.scallop.domain.entities.SongEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MusicRemoteImpl constructor(private val mApi: ItunesApi) : MusicDataStore {

    private val mMapper = MusicDataEntityMapper()

    override suspend fun getArtistsByName(
        name: String,
        page: Int
    ): Flow<ItunesApiResponseEntity<ArtistEntity>> {
        val offset = page * Properties.ITEMS_PER_PAGE
        val result = mApi.getArtistsByName(name, offset)
        return flow {
            result.body()?.let { emit(mMapper.mapArtistsToEntity(it)) }
        }
    }

    override suspend fun getAlbumsFromArtist(
        name: String,
        page: Int
    ): Flow<ItunesApiResponseEntity<AlbumEntity>> {
        val offset = page * Properties.ITEMS_PER_PAGE
        val result = mApi.getAlbumsFromArtist(name, offset)
        return flow {
            result.body()?.let { emit(mMapper.mapToEntity(it)) }
        }
    }

    override suspend fun getSongsFromAlbum(
        name: String,
        albumId: Long,
        page: Int
    ): Flow<ItunesApiResponseEntity<SongEntity>> {
        val offset = page * Properties.ITEMS_PER_PAGE
        val result = mApi.getSongsFromAlbum(name, offset)

        return flow {
            result.body()?.let {
                val validResults = it.results.filter { it1 -> it1.collectionId == albumId }
                emit(
                    ItunesApiResponseEntity(
                        validResults.size.toLong(),
                        mMapper.mapSongToEntity(validResults)
                    )
                )
            }
        }
    }
}