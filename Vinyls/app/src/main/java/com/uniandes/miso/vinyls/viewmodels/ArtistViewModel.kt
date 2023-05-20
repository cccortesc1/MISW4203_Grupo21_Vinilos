package com.uniandes.miso.vinyls.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.uniandes.miso.vinyls.models.Artist
import com.uniandes.miso.vinyls.repositories.ArtistsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(private val artistsRepository: ArtistsRepository) :
    ViewModel() {
    private val _artists = MutableLiveData<List<Artist>>()

    val artists: LiveData<List<Artist>>
        get() = _artists

    private var _eventNetworkError = MutableLiveData(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        artistsRepository.refreshData({
            _artists.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        }, {
            Log.d("Error", it.toString())
            _eventNetworkError.value = true
        })
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
}