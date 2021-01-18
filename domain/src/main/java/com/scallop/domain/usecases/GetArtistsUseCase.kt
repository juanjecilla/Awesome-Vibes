package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.entities.ArtistEntity
import com.scallop.domain.entities.ItunesApiResponseEntity
import com.scallop.domain.repositories.MusicRepository
import kotlinx.coroutines.flow.Flow

class GetArtistsUseCase(
    private val mRepository: MusicRepository
) : BaseUseCase<HashMap<String, Any>, Flow<List<ArtistEntity>>> {

    override suspend fun invoke(params: HashMap<String, Any>)= mRepository.getArtistsByName(params["name"] as String,
        params["page"] as Int
    )
}