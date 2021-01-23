package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.entities.SongEntity
import com.scallop.domain.repositories.MusicRepository

typealias SaveSongBaseUseCase = BaseUseCase<SongEntity, Unit>

class SaveSongUseCase(
    private val mRepository: MusicRepository
) : SaveSongBaseUseCase {

    override suspend fun invoke(params: SongEntity) {
        mRepository.saveSong(params)
    }
}