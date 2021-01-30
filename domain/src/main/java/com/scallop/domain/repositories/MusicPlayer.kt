package com.scallop.domain.repositories

interface MusicPlayer {

    fun playSong(url: String)
    fun stopSong()
}