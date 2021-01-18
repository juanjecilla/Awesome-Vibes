package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.entities.AlbumEntity
import com.scallop.domain.repositories.MusicRepository
import kotlinx.coroutines.flow.Flow

class GetAlbumsUseCase(
    private val mRepository: MusicRepository
) : BaseUseCase<HashMap<String, Any>, Flow<List<AlbumEntity>>> {

    override suspend fun invoke(params: HashMap<String, Any>) =
        mRepository.getAlbumsFromArtist(params["name"] as String, params["page"] as Int)
}