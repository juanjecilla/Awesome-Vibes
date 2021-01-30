package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.repositories.MusicPlayer

typealias PlaySongBaseUseCase = BaseUseCase<String, Unit>

class PlaySongUseCase(
    private val mMusicPlayer: MusicPlayer
) : PlaySongBaseUseCase {

    override suspend fun invoke(params: String) {
        mMusicPlayer.playSong(params)
    }
}