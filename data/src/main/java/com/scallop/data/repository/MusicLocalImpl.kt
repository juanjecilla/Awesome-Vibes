package com.scallop.data.repository

import com.scallop.data.db.MusicDao
import com.scallop.data.db.MusicDatabase
import com.scallop.data.mappers.MusicDataEntityMapper
import com.scallop.data.mappers.MusicEntityDataMapper
import com.scallop.domain.entities.*
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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

}