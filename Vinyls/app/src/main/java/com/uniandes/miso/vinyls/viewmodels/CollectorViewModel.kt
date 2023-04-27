package com.uniandes.miso.vinyls.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.uniandes.miso.vinyls.models.Collector
import com.uniandes.miso.vinyls.repositories.CollectorsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollectorViewModel @Inject constructor(private val collectorsRepository: CollectorsRepository) :
    ViewModel() {

    // private val collectorsRepository = CollectorsRepository(application)

    private val _collectors = MutableLiveData<List<Collector>>()

    val collectors: LiveData<List<Collector>>
        get() = _collectors

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
        collectorsRepository.refreshData({
            _collectors.postValue(it)
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
