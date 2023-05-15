package com.uniandes.miso.vinyls.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.uniandes.miso.vinyls.models.Album
import com.uniandes.miso.vinyls.repositories.AlbumsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(private val albumsRepository: AlbumsRepository) :
    ViewModel() {

    private val _albums = MutableLiveData<List<Album>>()

    val albums: LiveData<List<Album>>
        get() = _albums

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        viewModelScope.launch {
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
            try {
                withContext(Dispatchers.IO) {
                    val data = albumsRepository.refreshData()
                    _albums.postValue(data)
                }
            } catch (e: Exception) {
                Log.d("Error", e.toString())
                _eventNetworkError.value = true
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

}
