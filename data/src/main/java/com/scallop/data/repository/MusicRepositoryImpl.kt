package com.scallop.data.repository

import com.scallop.domain.entities.AlbumEntity
import com.scallop.domain.entities.ArtistEntity
import com.scallop.domain.entities.ItunesApiResponseEntity
import com.scallop.domain.entities.SongEntity
import com.scallop.domain.repositories.MusicRepository
import kotlinx.coroutines.flow.Flow

class MusicRepositoryImpl(
    private val mRemote: MusicRemoteImpl,
    private val mLocal: MusicLocalImpl
) : MusicRepository {

    override suspend fun getArtistsByName(
        name: String,
        page: Int
    ): Flow<ItunesApiResponseEntity<ArtistEntity>> {
        return mRemote.getArtistsByName(name, page)
    }

    override suspend fun getAlbumsFromArtist(
        name: String,
        page: Int
    ): Flow<ItunesApiResponseEntity<AlbumEntity>> {
        return mRemote.getAlbumsFromArtist(name, page)
    }

    override suspend fun getSongsFromAlbum(
        name: String,
        albumId: Long,
        page: Int
    ): Flow<ItunesApiResponseEntity<SongEntity>> {
        return mRemote.getSongsFromAlbum(name, albumId, page)
    }
}