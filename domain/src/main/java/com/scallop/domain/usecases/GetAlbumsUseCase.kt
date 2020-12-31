package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.entities.AlbumEntity
import com.scallop.domain.entities.ItunesApiResponseEntity
import com.scallop.domain.repositories.MusicRepository

class GetAlbumsUseCase(
    private val mRepository: MusicRepository
) : BaseUseCase<ItunesApiResponseEntity<AlbumEntity>>() {

    suspend fun getAlbums(name: String, page: Int) = mRepository.getAlbumsFromArtist(name, page)
}