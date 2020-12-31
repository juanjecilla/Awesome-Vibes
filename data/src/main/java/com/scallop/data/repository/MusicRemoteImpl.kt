package com.scallop.data.repository

import android.util.Log
import com.scallop.data.api.ItunesApi
import com.scallop.data.mappers.MusicDataEntityMapper
import com.scallop.domain.entities.AlbumEntity
import com.scallop.domain.entities.ArtistEntity
import com.scallop.domain.entities.ItunesApiResponseEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MusicRemoteImpl constructor(private val mApi: ItunesApi) : MusicDataStore {

    private val mMapper = MusicDataEntityMapper()

    override suspend fun getArtistsByName(
        name: String,
        page: Int
    ): Flow<ItunesApiResponseEntity<ArtistEntity>> {
        val result = mApi.getArtistsByName(name)
        return flow {
            result.body()?.let { emit(mMapper.mapArtistsToEntity(it)) }
        }
    }

    override suspend fun getAlbumsFromArtist(
        name: String,
        page: Int
    ): Flow<ItunesApiResponseEntity<AlbumEntity>> {
        return mApi.getAlbumsFromArtist(name).map { mMapper.mapToEntity(it) }
    }
}