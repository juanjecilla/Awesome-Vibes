package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.entities.ItunesApiResponseEntity
import com.scallop.domain.entities.MusicVideoEntity
import com.scallop.domain.repositories.MusicRepository

class GetMusicVideoUseCase(
    private val mRepository: MusicRepository
) : BaseUseCase<ItunesApiResponseEntity<MusicVideoEntity>>() {

    suspend fun getMusicVideo(name: String, trackId: Long) =
        mRepository.getMusicVideo(name, trackId)
}