package com.uniandes.miso.vinyls.repositories

import android.content.Context
import com.uniandes.miso.vinyls.models.Album
import com.uniandes.miso.vinyls.network.NetworkServiceAdapter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Singleton
class AlbumsRepository(@ApplicationContext val application: Context) {
    suspend fun refreshData(): List<Album> {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getAlbums()
    }
}