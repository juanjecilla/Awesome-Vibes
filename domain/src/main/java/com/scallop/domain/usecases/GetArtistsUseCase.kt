package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.entities.ArtistEntity
import com.scallop.domain.repositories.MusicRepository
import kotlinx.coroutines.flow.Flow

typealias GetArtistsBaseUseCase = BaseUseCase<GetArtistsUseCase.Params, Flow<List<ArtistEntity>>>

class GetArtistsUseCase(
    private val mRepository: MusicRepository
) : GetArtistsBaseUseCase {

    override suspend fun invoke(params: Params) = mRepository.getArtistsByName(
        params.name,
        params.page
    )

    data class Params(
        val name: String,
        val page: Int
    )
}