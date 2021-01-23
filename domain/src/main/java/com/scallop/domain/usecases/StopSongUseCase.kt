package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.repositories.MusicPlayer

typealias StopSongBaseUseCase = BaseUseCase<Unit?, Unit>

class StopSongUseCase(
    private val mMusicPlayer: MusicPlayer
) : StopSongBaseUseCase {

    override suspend fun invoke(params: Unit?) {
        mMusicPlayer.stopSong()
    }
}