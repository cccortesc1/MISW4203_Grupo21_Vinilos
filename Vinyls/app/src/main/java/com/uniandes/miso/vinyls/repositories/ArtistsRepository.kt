package com.uniandes.miso.vinyls.repositories

import android.content.Context
import com.android.volley.VolleyError
import com.uniandes.miso.vinyls.models.Artist
import com.uniandes.miso.vinyls.network.NetworkServiceAdapter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Singleton
class ArtistsRepository(@ApplicationContext val application: Context) {
    suspend fun refreshData(): List<Artist> {
        return NetworkServiceAdapter.getInstance(application).getArtists();
    }
}