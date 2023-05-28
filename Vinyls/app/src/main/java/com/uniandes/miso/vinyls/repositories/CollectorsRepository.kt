package com.uniandes.miso.vinyls.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.uniandes.miso.vinyls.database.dao.CollectorsDao
import com.uniandes.miso.vinyls.models.Collector
import com.uniandes.miso.vinyls.network.NetworkServiceAdapter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Singleton
class CollectorsRepository (val application: Application, private val collectorsDao: CollectorsDao){
    suspend fun refreshData(): List<Collector>{
        var cached = collectorsDao.getCollectors()
        return if(cached.isEmpty()){
            val cm = application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if( cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE){
                emptyList()
            } else NetworkServiceAdapter.getInstance(application).getCollectors()
        } else cached
    }
}