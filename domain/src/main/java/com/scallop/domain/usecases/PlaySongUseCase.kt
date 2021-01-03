package com.scallop.domain.usecases

import com.scallop.domain.common.BaseUseCase
import com.scallop.domain.entities.AlbumEntity
import com.scallop.domain.entities.ItunesApiResponseEntity
import com.scallop.domain.repositories.MusicPlayer

class PlaySongUseCase(
    private val mMusicPlayer: MusicPlayer
) : BaseUseCase<ItunesApiResponseEntity<AlbumEntity>>() {

    fun playSong(url: String) {
        mMusicPlayer.playSong(url)
    }

    fun stopSong() {
        mMusicPlayer.stopSong()
    }
}