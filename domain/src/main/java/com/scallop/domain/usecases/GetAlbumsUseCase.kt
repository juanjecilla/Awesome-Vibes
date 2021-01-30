package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.entities.AlbumEntity
import com.scallop.domain.repositories.MusicRepository
import kotlinx.coroutines.flow.Flow

typealias GetAlbumsBaseUseCase = BaseUseCase<GetAlbumsUseCase.Params, Flow<List<AlbumEntity>>>

class GetAlbumsUseCase(
    private val mRepository: MusicRepository
) : GetAlbumsBaseUseCase {

    override suspend fun invoke(params: Params) =
        mRepository.getAlbumsFromArtist(params.name, params.page)

    data class Params(
        val name: String,
        val page: Int
    )
}