package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.entities.MusicVideoEntity
import com.scallop.domain.repositories.MusicRepository
import kotlinx.coroutines.flow.Flow

typealias GetMusicVideoBaseUseCase = BaseUseCase<GetMusicVideoUseCase.Params, Flow<MusicVideoEntity?>>

class GetMusicVideoUseCase(
    private val mRepository: MusicRepository
) : GetMusicVideoBaseUseCase {

    override suspend fun invoke(params: Params) =
        mRepository.getMusicVideo(params.name, params.trackId)

    data class Params(
        val name: String,
        val trackId: Long
    )
}