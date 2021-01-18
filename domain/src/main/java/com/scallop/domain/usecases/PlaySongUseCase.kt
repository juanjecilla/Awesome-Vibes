package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.repositories.MusicPlayer

class PlaySongUseCase(
    private val mMusicPlayer: MusicPlayer
): BaseUseCase<String, Unit> {

    override suspend fun invoke(params: String) {
        mMusicPlayer.playSong(params)
    }
}