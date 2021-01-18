package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.entities.AlbumEntity
import com.scallop.domain.entities.SongEntity
import com.scallop.domain.repositories.MusicRepository
import kotlinx.coroutines.flow.Flow

class GetSongsUseCase(
    private val mRepository: MusicRepository
) : BaseUseCase<HashMap<String, Any>, Flow<List<SongEntity>>> {

    override suspend fun invoke(params: HashMap<String, Any>) =
        mRepository.getSongsFromAlbum(
            params["name"] as String, params["albumId"] as Long,
            params["page"] as Int
        )
}