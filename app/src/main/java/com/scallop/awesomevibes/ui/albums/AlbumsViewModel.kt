package com.scallop.awesomevibes.ui.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scallop.awesomevibes.entities.Album
import com.scallop.awesomevibes.entities.Data
import com.scallop.awesomevibes.entities.Status
import com.scallop.awesomevibes.mappers.AlbumsMapper
import com.scallop.domain.usecases.GetAlbumsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumsViewModel(
    private val mUseCase: GetAlbumsUseCase,
    private val mMapper: AlbumsMapper
) : ViewModel() {

    private val _data = MutableLiveData<Data<List<Album>>>()
    val data: LiveData<Data<List<Album>>> get() = _data

    fun getAlbums(searchName: String, page: Int = 0) {
        _data.value = Data(Status.LOADING)
        viewModelScope.launch {
            val results = withContext(Dispatchers.IO) {
                mUseCase.getAlbums(searchName, page)
            }
            results.map {
                _data.value = Data(Status.SUCCESSFUL, mMapper.mapAlbum(it.results))
            }.collect()
        }
    }
}