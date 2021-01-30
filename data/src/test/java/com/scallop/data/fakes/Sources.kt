package com.scallop.data.fakes

import com.scallop.data.entitites.ArtistData
import com.scallop.data.repository.LocalDataSource
import com.scallop.data.repository.RemoteDataSource
import com.scallop.domain.entities.AlbumEntity
import com.scallop.domain.entities.ArtistEntity
import com.scallop.domain.entities.MusicVideoEntity
import com.scallop.domain.entities.SongEntity
import kotlinx.coroutines.flow.Flow

class FakeLocalDataSource : LocalDataSource {
    override suspend fun getSongsFromAlbum(albumId: Long): Flow<List<SongEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveSong(song: SongEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSong(song: SongEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getSong(songId: Int): SongEntity? {
        TODO("Not yet implemented")
    }

}

class FakeRemoteDataSource<T>(
    private val data: List<T> = emptyList(),
    private val delay: Long = 0
) : RemoteDataSource {
    override suspend fun getArtistsByName(name: String, page: Int): Flow<List<ArtistEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAlbumsFromArtist(name: String, page: Int): Flow<List<AlbumEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSongsFromAlbum(
        name: String,
        albumId: Long,
        page: Int
    ): Flow<List<SongEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMusicVideo(name: String, trackId: Long): Flow<MusicVideoEntity> {
        TODO("Not yet implemented")
    }

}
