package com.scallop.awesomevibes.ui.songs

import com.scallop.awesomevibes.entities.Song

interface OnSongItemInteractor {
    fun onItemClicked(song: Song)
}