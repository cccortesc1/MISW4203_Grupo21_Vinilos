package com.uniandes.miso.vinyls.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.uniandes.miso.vinyls.models.Album
import com.uniandes.miso.vinyls.models.Artist
import com.uniandes.miso.vinyls.models.Collector
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class NetworkServiceAdapter constructor(context: Context) {
    companion object {
        const val BASE_URL = "https://miso-mobile-vynils-backend.herokuapp.com/"
        private var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }

    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    suspend fun getCollectors() = suspendCoroutine<List<Collector>> { cont ->
        requestQueue.add(
            getRequest("collectors",
                { response ->
                    Log.d("tagb", response)
                    val resp = JSONArray(response)
                    val list = mutableListOf<Collector>()
                    for (i in 0 until resp.length()) {
                        val item = resp.getJSONObject(i)
                        val gson = Gson()
                        val collector = gson.fromJson(item.toString(), Collector::class.java)
                        list.add(collector)
                    }

                    cont.resume(list)
                },
                {
                    cont.resumeWithException(it)
                })
        )
    }

    suspend fun getArtists() = suspendCoroutine<List<Artist>> { cont->
        requestQueue.add(
            getRequest("musicians",
                { response ->
                    //Log.d("tagb", response)
                    val resp = JSONArray(response)
                    val list = mutableListOf<Artist>()
                    for (i in 0 until resp.length()) {
                        val item = resp.getJSONObject(i)
                        val detailAlbum = item.getJSONArray("albums")
                        val albumList = mutableListOf<Album>();

                        for (j in 0 until detailAlbum.length()) {
                            val detailItem = detailAlbum.getJSONObject(j)
                            albumList.add(
                                Album(
                                    albumId = detailItem.getInt("id"),
                                    name = detailItem.getString("name"),
                                    cover = detailItem.getString("cover"),
                                    releaseDate = detailItem.getString("releaseDate"),
                                    description = detailItem.getString("description"),
                                    genre = detailItem.getString("genre"),
                                    recordLabel = detailItem.getString("recordLabel")
                                )
                            )
                        }

                        list.add(
                            i,
                            Artist(
                                artistId = item.getInt("id"),
                                name = item.getString("name"),
                                image = item.getString("image"),
                                description = item.getString("description"),
                                birthDate = item.getString("birthDate"),
                                albums = albumList
                            )
                        )
                    }
                    cont.resume(list)
                },
                {
                    cont.resumeWithException(it)
                })
        )
    }

    private fun getRequest(
        path: String,
        responseListener: Response.Listener<String>,
        errorListener: Response.ErrorListener
    ): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL + path, responseListener, errorListener)
    }

    private fun postRequest(
        path: String,
        body: JSONObject,
        responseListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ): JsonObjectRequest {
        return JsonObjectRequest(
            Request.Method.POST,
            BASE_URL + path,
            body,
            responseListener,
            errorListener
        )
    }

    private fun putRequest(
        path: String,
        body: JSONObject,
        responseListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ): JsonObjectRequest {
        return JsonObjectRequest(
            Request.Method.PUT,
            BASE_URL + path,
            body,
            responseListener,
            errorListener
        )
    }

    suspend fun getAlbums() = suspendCoroutine<List<Album>> { cont ->
        requestQueue.add(
            getRequest("albums",
                { response ->
                    Log.d("tagb", response)
                    val resp = JSONArray(response)
                    val list = mutableListOf<Album>()
                    for (i in 0 until resp.length()) {
                        val item = resp.getJSONObject(i)
                        list.add(
                            i,
                            Album(
                                albumId = item.getInt("id"),
                                name = item.getString("name"),
                                cover = item.getString("cover"),
                                releaseDate = item.getString("releaseDate"),
                                description = item.getString("description"),
                                genre = item.getString("genre"),
                                recordLabel = item.getString("recordLabel")
                            )
                        )
                        val gson = Gson()
                        val album = gson.fromJson(item.toString(), Album::class.java)
                        list.add(album)
                    }
                    cont.resume(list)
                },
                {
                    cont.resumeWithException(it)
                })
        )
    }
}