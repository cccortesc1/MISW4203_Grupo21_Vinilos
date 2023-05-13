package com.uniandes.miso.vinyls.viewmodels

import androidx.lifecycle.*
import com.uniandes.miso.vinyls.models.Artist
import com.uniandes.miso.vinyls.repositories.ArtistsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(private val artistsRepository: ArtistsRepository) :
    ViewModel() {
    private val _artists = MutableLiveData<List<Artist>>()

    val artists: LiveData<List<Artist>>
        get() = _artists

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
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    var data = artistsRepository.refreshData()
                    _artists.postValue(data)
                }
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            }
        }
        catch (e:Exception){
            //Log.d("Error", e.toString())
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
}