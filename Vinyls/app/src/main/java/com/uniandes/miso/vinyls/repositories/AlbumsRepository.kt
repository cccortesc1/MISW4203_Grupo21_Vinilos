package com.uniandes.miso.vinyls.repositories

import android.content.Context
import com.android.volley.VolleyError
import com.uniandes.miso.vinyls.models.Album
import com.uniandes.miso.vinyls.network.NetworkServiceAdapter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Singleton
class AlbumsRepository(@ApplicationContext val application: Context) {
    fun refreshData(callback: (List<Album>) -> Unit, onError: (VolleyError) -> Unit) {
        NetworkServiceAdapter.getInstance(application).getAlbums(
            {
                //Guardar los coleccionistas de la variable it en un almacén de datos local para uso futuro
                callback(it)
            },
            onError
        )
    }

}