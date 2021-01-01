package com.scallop.awesomevibes.ui.songs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
) : ViewModel() {

    private val _data = MutableLiveData<Data<List<Song>>>()
    val data: LiveData<Data<List<Song>>> get() = _data

    fun getSongs(albumName: String, albumId: Long, page: Int = 0) {
        _data.value = Data(Status.LOADING)
        viewModelScope.launch {
            val results = withContext(Dispatchers.IO) {
                mUseCase.getSongs(albumName, albumId, page)
            }
            results.map {
                _data.value = Data(Status.SUCCESSFUL, mMapper.mapSongs(it.results))
            }.collect()
        }
    }
}