package com.scallop.data.repository

import com.scallop.data.db.MusicDao
import com.scallop.data.mappers.MusicDataEntityMapper
import com.scallop.data.mappers.MusicEntityDataMapper
import com.scallop.domain.entities.AlbumEntity
import com.scallop.domain.entities.ArtistEntity
import com.scallop.domain.entities.MusicVideoEntity
import com.scallop.domain.entities.SongEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MusicLocalImpl(
    private val mDao: MusicDao,
    private val mDataEntityMapper: MusicDataEntityMapper,
    private val mEntityDataMapper: MusicEntityDataMapper,
) : MusicDataStore {

    override suspend fun getArtistsByName(
        name: String,
        page: Int
    ): Flow<List<ArtistEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAlbumsFromArtist(
        name: String,
        page: Int
    ): Flow<List<AlbumEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSongsFromAlbum(
        name: String,
        albumId: Long,
        page: Int
    ): Flow<List<SongEntity>> {
        return mDao.getSavedSongs(albumId).map { mDataEntityMapper.mapSongToEntity(it) }
    }

    fun saveSong(song: SongEntity) {
        mDao.insertSong(mEntityDataMapper.mapSongToData(song))
    }

    fun deleteSong(song: SongEntity) {
        mDao.deleteSong(mEntityDataMapper.mapSongToData(song))
    }

    fun getSong(songId: Int): SongEntity? {
        return mDao.getSavedSong(songId)?.let { mDataEntityMapper.mapSongToEntity(it) }
    }

    override suspend fun getMusicVideo(name: String, trackId: Long): Flow<MusicVideoEntity> {
        TODO("Not yet implemented")
    }
}