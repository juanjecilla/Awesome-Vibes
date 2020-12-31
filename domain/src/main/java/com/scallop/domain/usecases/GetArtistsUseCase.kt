package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.entities.ArtistEntity
import com.scallop.domain.entities.ItunesApiResponseEntity
import com.scallop.domain.repositories.MusicRepository

class GetArtistsUseCase(
    private val mRepository: MusicRepository
) : BaseUseCase<ItunesApiResponseEntity<ArtistEntity>>() {

    suspend fun getArtists(name: String, page: Int) = mRepository.getArtistsByName(name, page)
}