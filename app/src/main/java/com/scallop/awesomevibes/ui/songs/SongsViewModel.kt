package com.scallop.awesomevibes.ui.songs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.scallop.awesomevibes.common.BaseViewModel
import com.scallop.awesomevibes.entities.Data
import com.scallop.awesomevibes.entities.Song
import com.scallop.awesomevibes.entities.Status
import com.scallop.awesomevibes.mappers.SongsMapper
import com.scallop.domain.usecases.GetSongsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SongsViewModel(
    private val mUseCase: GetSongsUseCase,
    private val mMapper: SongsMapper
) : BaseViewModel() {

    private val _data = MutableLiveData<Data<List<Song>>>()
    val data: LiveData<Data<List<Song>>> get() = _data

    fun getSongs(albumName: String, albumId: Long, page: Int = 0) {
        _data.value = Data(Status.LOADING)
        viewModelScope.launch {
            val results = withContext(Dispatchers.IO) {
                mUseCase.getSongs(albumName, albumId, page)
            }
            results.map {
                _data.value = Data(Status.SUCCESSFUL, data.value?.data?.let {it1 -> it1 + mMapper.mapSongs(it) } )
            }.collect()
        }
    }

    fun saveSong(song: Song) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                mUseCase.saveSong(mMapper.mapSong(song))
            }
        }
    }
}