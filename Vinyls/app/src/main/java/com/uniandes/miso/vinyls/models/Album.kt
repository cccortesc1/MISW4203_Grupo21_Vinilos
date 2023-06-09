package com.uniandes.miso.vinyls.models

import android.net.Uri
import com.google.gson.Gson

data class Album (
    val albumId: Int,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String,
    var tracks: List<Track>,
    var performers: List<Performer>,
    var comments: List<Comment>
) {
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}

data class Performer (
    val id: Long,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String
)

data class Track (
    val trackId: Int,
    val name: String,
    val duration: String
)

data class TrackAssociated (
    val name: String,
    val duration: String
)
