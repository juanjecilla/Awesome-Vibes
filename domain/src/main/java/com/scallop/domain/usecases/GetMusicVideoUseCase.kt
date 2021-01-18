package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.entities.MusicVideoEntity
import com.scallop.domain.repositories.MusicRepository
import kotlinx.coroutines.flow.Flow

class GetMusicVideoUseCase(
    private val mRepository: MusicRepository
) : BaseUseCase<HashMap<String, Any>, Flow<MusicVideoEntity?>> {

    override suspend fun invoke(params: HashMap<String, Any>) =
        mRepository.getMusicVideo(params["name"] as String, params["trackId"] as Long)

}