package com.uniandes.miso.vinyls.repositories

import android.content.Context
import com.uniandes.miso.vinyls.models.Album
import com.uniandes.miso.vinyls.models.NewAlbum
import com.uniandes.miso.vinyls.models.Track
import com.uniandes.miso.vinyls.models.TrackAssociated
import com.uniandes.miso.vinyls.network.NetworkServiceAdapter
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONObject
import javax.inject.Singleton

@Singleton
class AlbumsRepository(@ApplicationContext val application: Context) {
    suspend fun refreshData(): List<Album> {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getAlbums()
    }

    suspend fun associateTrack(track: TrackAssociated, idAlbum: Int) : JSONObject {
        return NetworkServiceAdapter.getInstance(application).associateTrack(track, idAlbum)
    }

    suspend fun createNewAlbum(album: NewAlbum): JSONObject {
        return NetworkServiceAdapter.getInstance(application).createNewAlbum(album)
    }
}