package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.entities.SongEntity
import com.scallop.domain.repositories.MusicRepository

class SaveSongUseCase(
    private val mRepository: MusicRepository
) : BaseUseCase<SongEntity, Unit> {

    override suspend fun invoke(params: SongEntity) {
        mRepository.saveSong(params)
    }
}