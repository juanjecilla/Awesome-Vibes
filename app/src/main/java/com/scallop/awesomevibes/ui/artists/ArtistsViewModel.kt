package com.scallop.awesomevibes.ui.artists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scallop.awesomevibes.entities.Artist
import com.scallop.awesomevibes.entities.Data
import com.scallop.awesomevibes.entities.Status
import com.scallop.awesomevibes.mappers.ArtistMapper
import com.scallop.domain.usecases.GetArtistsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistsViewModel(
    private val mUseCase: GetArtistsUseCase,
    private val mMapper: ArtistMapper
) : ViewModel() {

    private val _data = MutableLiveData<Data<List<Artist>>>()
    val data: LiveData<Data<List<Artist>>> get() = _data


    fun getArtists(searchName: String, page: Int = 0) {
        _data.value = Data(Status.LOADING)
        viewModelScope.launch {
            val results = withContext(Dispatchers.IO) {
                mUseCase.getArtists(searchName, page)
            }
            results.map {
                _data.value = Data(Status.SUCCESSFUL, mMapper.mapArtist(it))
            }.collect()
        }
    }
}