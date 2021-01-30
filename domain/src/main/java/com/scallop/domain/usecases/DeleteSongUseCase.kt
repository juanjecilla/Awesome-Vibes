package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.entities.SongEntity
import com.scallop.domain.repositories.MusicRepository

typealias DeleteSongBaseUseCase = BaseUseCase<SongEntity, Unit>

class DeleteSongUseCase(
    private val mRepository: MusicRepository
) : DeleteSongBaseUseCase {

    override suspend fun invoke(params: SongEntity) {
        mRepository.deleteSong(params)
    }
}