package com.uniandes.miso.vinyls.repositories

import android.content.Context
import com.android.volley.VolleyError
import com.uniandes.miso.vinyls.models.Artist
import com.uniandes.miso.vinyls.network.NetworkServiceAdapter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Singleton
class ArtistsRepository(@ApplicationContext val application: Context) {
    fun refreshData(callback: (List<Artist>) -> Unit, onError: (VolleyError) -> Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getArtists(
            {
                //Guardar los coleccionistas de la variable it en un almacén de datos local para uso futuro
                callback(it)
            },
            onError
        )
    }
}