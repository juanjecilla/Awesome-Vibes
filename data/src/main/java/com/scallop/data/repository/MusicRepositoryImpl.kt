package com.scallop.data.repository

import com.scallop.domain.entities.AlbumEntity
import com.scallop.domain.entities.ArtistEntity
import com.scallop.domain.entities.MusicVideoEntity
import com.scallop.domain.entities.SongEntity
import com.scallop.domain.repositories.MusicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class MusicRepositoryImpl(
    private val mRemote: RemoteDataSource,
    private val mLocal: LocalDataSource
) : MusicRepository {

    override suspend fun getArtistsByName(
        name: String,
        page: Int
    ): Flow<List<ArtistEntity>> {
        return mRemote.getArtistsByName(name, page)
    }

    override suspend fun getAlbumsFromArtist(
        name: String,
        page: Int
    ): Flow<List<AlbumEntity>> {
        return mRemote.getAlbumsFromArtist(name, page)
    }

    override suspend fun getSongsFromAlbum(
        name: String,
        albumId: Long,
        page: Int
    ): Flow<List<SongEntity>> {
        val remoteSongs = mRemote.getSongsFromAlbum(name, albumId, page)
        val localSongs = mLocal.getSongsFromAlbum(albumId)
        return remoteSongs.combine(localSongs) { remote, local ->
            remote.map {
                it.also {
                    it.savedSong = local.any { it1 -> it.trackId == it1.trackId }
                }
            }
        }
    }

    override suspend fun saveSong(song: SongEntity) {
        mLocal.saveSong(song)
    }

    override suspend fun deleteSong(song: SongEntity) {
        mLocal.deleteSong(song)
    }

    override suspend fun getMusicVideo(name: String, trackId: Long): Flow<MusicVideoEntity?> {
        return mRemote.getMusicVideo(name, trackId)
    }
}