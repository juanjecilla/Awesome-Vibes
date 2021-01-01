package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.entities.AlbumEntity
import com.scallop.domain.entities.ItunesApiResponseEntity
import com.scallop.domain.entities.SongEntity
import com.scallop.domain.repositories.MusicRepository

class GetSongsUseCase(
    private val mRepository: MusicRepository
) : BaseUseCase<ItunesApiResponseEntity<AlbumEntity>>() {

    suspend fun getSongs(name: String, albumId: Long, page: Int) =
        mRepository.getSongsFromAlbum(name, albumId, page)

    suspend fun saveSong(song: SongEntity) {
        mRepository.saveSong(song)
    }
}