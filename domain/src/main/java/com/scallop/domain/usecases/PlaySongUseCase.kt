package com.scallop.domain.usecases

import com.scallop.domain.repositories.MusicPlayer

class PlaySongUseCase(
    private val mMusicPlayer: MusicPlayer
) {

    fun playSong(url: String) {
        mMusicPlayer.playSong(url)
    }

    fun stopSong() {
        mMusicPlayer.stopSong()
    }
}