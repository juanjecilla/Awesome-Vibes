package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.repositories.MusicPlayer

class StopSongUseCase(
    private val mMusicPlayer: MusicPlayer
) : BaseUseCase<Unit?, Unit> {

    override suspend fun invoke(params: Unit?) {
        mMusicPlayer.stopSong()
    }
}