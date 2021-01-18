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
import com.scallop.domain.usecases.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SongsViewModel(
    private val mAlbumName: String,
    private val mAlbumId: Long,
    private val mGetSongsUseCase: GetSongsUseCase,
    private val mPlaySongUseCase: PlaySongUseCase,
    private val mStopSongUseCase: StopSongUseCase,
    private val mGetMusicVideoUseCase: GetMusicVideoUseCase,
    private val mSaveSongUseCase: SaveSongUseCase,
    private val mDeleteSongUseCase: DeleteSongUseCase,
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
                    val params = HashMap<String, Any>()
                    params["name"] = mAlbumName
                    params["albumId"] = mAlbumId
                    params["page"] = page

                    mGetSongsUseCase(params)
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
            withContext(Dispatchers.IO) {
                if (song.savedSong) {
                    mSaveSongUseCase(mMapper.mapSong(song))
                } else {
                    mDeleteSongUseCase(mMapper.mapSong(song))
                }
            }
        }
    }

    fun playSong(url: String) {
        viewModelScope.launch {
            mPlaySongUseCase(url)
        }
    }

    fun stopSong() {
        viewModelScope.launch {
            mStopSongUseCase(null)
        }
    }

    fun getMusicVideo(trackName: String, trackId: Long) {
        viewModelScope.launch {
            val results = withContext(Dispatchers.IO) {
                val params = HashMap<String, Any>()
                params["name"] = trackName
                params["trackId"] = trackId

                mGetMusicVideoUseCase(params)
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