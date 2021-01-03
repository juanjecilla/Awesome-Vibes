package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.entities.AlbumEntity
import com.scallop.domain.entities.ItunesApiResponseEntity
import com.scallop.domain.entities.SongEntity
import com.scallop.domain.repositories.MusicRepository

class SaveSongUseCase(
    private val mRepository: MusicRepository
) : BaseUseCase<SongEntity>() {

    suspend fun saveSong(song: SongEntity) {
        mRepository.saveSong(song)
    }

    suspend fun deleteSong(song: SongEntity) {
        mRepository.deleteSong(song)
    }
}