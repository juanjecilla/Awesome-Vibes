package com.scallop.data.managers

import android.media.MediaPlayer
import com.scallop.domain.repositories.MusicPlayer

class MusicPlayerImpl(private val mMediaPlayer: MediaPlayer) : MusicPlayer {
    override fun playSong(url: String) {
        stopSong()
        mMediaPlayer.setDataSource(url)
        mMediaPlayer.prepare()
        mMediaPlayer.start()
    }

    override fun stopSong() {
        mMediaPlayer.stop()
        mMediaPlayer.reset()
    }
}