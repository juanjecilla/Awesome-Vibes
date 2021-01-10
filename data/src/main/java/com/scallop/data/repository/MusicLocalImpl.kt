package com.scallop.data.repository

import com.scallop.data.db.MusicDao
import com.scallop.data.mappers.MusicDataEntityMapper
import com.scallop.data.mappers.MusicEntityDataMapper
import com.scallop.domain.entities.SongEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MusicLocalImpl(
    private val mDao: MusicDao,
    private val mDataEntityMapper: MusicDataEntityMapper,
    private val mEntityDataMapper: MusicEntityDataMapper,
) : LocalDataSource {

    override suspend fun getSongsFromAlbum(
        albumId: Long
    ): Flow<List<SongEntity>> {
        return mDao.getSavedSongs(albumId).map { mDataEntityMapper.mapSongToEntity(it) }
    }

    override suspend fun saveSong(song: SongEntity) {
        mDao.insertSong(mEntityDataMapper.mapSongToData(song))
    }

    override suspend fun deleteSong(song: SongEntity) {
        mDao.deleteSong(mEntityDataMapper.mapSongToData(song))
    }

    override suspend fun getSong(songId: Int): SongEntity? {
        return mDao.getSavedSong(songId)?.let { mDataEntityMapper.mapSongToEntity(it) }
    }
}