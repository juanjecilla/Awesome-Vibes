package com.scallop.awesomevibes.ui.songs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scallop.awesomevibes.entities.Data
import com.scallop.awesomevibes.entities.MusicVideo
import com.scallop.awesomevibes.entities.Song
import com.scallop.awesomevibes.entities.Status
import com.scallop.awesomevibes.mappers.SongsMapper
import com.scallop.domain.usecases.GetMusicVideoUseCase
import com.scallop.domain.usecases.GetSongsUseCase
import com.scallop.domain.usecases.PlaySongUseCase
import com.scallop.domain.usecases.SaveSongUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SongsViewModel(
    private val mAlbumName: String,
    private val mAlbumId: Long,
    private val mGetSongsUseCase: GetSongsUseCase,
    private val mPlaySongUseCase: PlaySongUseCase,
    private val mGetMusicVideoUseCase: GetMusicVideoUseCase,
    private val mSaveSongUseCase: SaveSongUseCase,
    private val mMapper: SongsMapper
) : ViewModel() {

    private var shouldLoadMore = true

    private val _data = MutableLiveData<Data<List<Song>>>()
    val data: LiveData<Data<List<Song>>> get() = _data

    private val _video = MutableLiveData<Data<MusicVideo>>()
    val video: LiveData<Data<MusicVideo>> get() = _video

    init {
        getSongs(0)
    }

    fun getSongs(page: Int = 0) {
        if (shouldLoadMore) {
            _data.value = Data(Status.LOADING)
            viewModelScope.launch {
                val results = withContext(Dispatchers.IO) {
                    mGetSongsUseCase.getSongs(mAlbumName, mAlbumId, page)
                }
                results.map {
                    if (it.isEmpty()) {
                        shouldLoadMore = false
                    }
                    _data.value = Data(Status.SUCCESSFUL, mMapper.mapSongs(it))
                }.collect()
            }
        }
    }

    fun saveSong(song: Song) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                if (song.savedSong) {
                    mSaveSongUseCase.saveSong(mMapper.mapSong(song))
                } else {
                    mSaveSongUseCase.deleteSong(mMapper.mapSong(song))
                }
            }
        }
    }

    fun playSong(url: String) {
        mPlaySongUseCase.playSong(url)
    }

    fun stopSong() {
        mPlaySongUseCase.stopSong()
    }

    fun getMusicVideo(trackName: String, trackId: Long) {
        viewModelScope.launch {
            val results = withContext(Dispatchers.IO) {
                mGetMusicVideoUseCase.getMusicVideo(trackName, trackId)
            }
            results.map {
                it?.let {
                    _video.value = Data(Status.SUCCESSFUL, mMapper.mapMusicVideo(it))
                } ?: run {
                    _video.value = Data(Status.ERROR)
                }
            }.collect()
        }
    }
}