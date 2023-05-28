package com.uniandes.miso.vinyls.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.uniandes.miso.vinyls.database.VinylRoomDatabase
import com.uniandes.miso.vinyls.models.Collector
import com.uniandes.miso.vinyls.repositories.ArtistsRepository
import com.uniandes.miso.vinyls.repositories.CollectorsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CollectorViewModel @Inject constructor(application:  Application) :
    ViewModel() {

    private val collectorsRepository = CollectorsRepository(application, VinylRoomDatabase.getDatabase(application.applicationContext).collectorsDao())

    private val _collectors = MutableLiveData<List<Collector>>()

    val collectors: LiveData<List<Collector>>
        get() = _collectors

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
        viewModelScope.launch {
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
            try {
                withContext(Dispatchers.IO) {
                    val data = collectorsRepository.refreshData()
                    _collectors.postValue(data)
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
