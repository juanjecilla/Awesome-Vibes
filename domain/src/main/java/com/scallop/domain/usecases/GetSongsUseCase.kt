package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.entities.SongEntity
import com.scallop.domain.repositories.MusicRepository
import kotlinx.coroutines.flow.Flow

class GetSongsUseCase(
    private val mRepository: MusicRepository
) : BaseUseCase<GetSongsUseCase.Params, Flow<List<SongEntity>>> {

    override suspend fun invoke(params: Params) =
        mRepository.getSongsFromAlbum(params.name, params.albumId, params.page)

    data class Params(
        val name: String,
        val albumId: Long,
        val page: Int
    )
}