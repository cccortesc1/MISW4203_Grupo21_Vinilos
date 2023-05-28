package com.uniandes.miso.vinyls.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.*
import com.uniandes.miso.vinyls.models.Album
import com.uniandes.miso.vinyls.models.NewAlbum
import com.uniandes.miso.vinyls.models.TrackAssociated
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

    private var _eventNetworkError = MutableLiveData(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private var _associateTrack = MutableLiveData(false)

    val associateTrack: LiveData<Boolean>
        get() = _associateTrack

    private var _createAlbum = MutableLiveData(false)

    val createAlbum: LiveData<Boolean>
        get() = _createAlbum


    private var _loadingTrack = MutableLiveData(false)

    val loadingTrack: LiveData<Boolean>
        get() = _loadingTrack

    var trackName = mutableStateOf(TextFieldValue())
    var duration = mutableStateOf(TextFieldValue())
    val albumName = mutableStateOf(TextFieldValue())
    val cover = mutableStateOf(TextFieldValue())
    val description = mutableStateOf(TextFieldValue())
    val genre = mutableStateOf(String())
    val recordLabel = mutableStateOf(String())
    val date =  mutableStateOf(String())

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

    fun associateTrack(track: TrackAssociated, idAlbum: Int) {
        _loadingTrack.postValue(true)
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    val response = albumsRepository.associateTrack(track, 100)
                    // val response = albumsRepository.associateTrack(track, idAlbum)
                    _associateTrack.postValue(true)
                    _loadingTrack.postValue(false)
                }

            } catch (e: Exception) {
                Log.d("Error", e.toString())
                _associateTrack.postValue(false)
                _loadingTrack.postValue(false)
            }
        }
    }

    fun createNewAlbum(album: NewAlbum) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    val response = albumsRepository.createNewAlbum(album)
                    _createAlbum.postValue(true)
                }

            } catch (e: Exception) {
                Log.d("Error", e.toString())
                Log.d("ErrorM", e.message.toString())
                _createAlbum.postValue(false)
            }
        }
    }
}
