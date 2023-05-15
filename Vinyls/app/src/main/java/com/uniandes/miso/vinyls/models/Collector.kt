package com.uniandes.miso.vinyls.models

import android.net.Uri
import com.google.gson.Gson

data class Collector(
    val collectorId: Int,
    val name: String,
    val telephone: String,
    val email: String,
    val comments: List<Comment>,
    val favoritePerformers: List<FavoritePerformer>,
    val collectorAlbums: List<CollectorAlbum>
){
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}

data class CollectorAlbum (
    val id: Long,
    val price: Long,
    val status: String
)

data class Comment (
    val id: Long,
    val description: String,
    val rating: Long
)

data class FavoritePerformer (
    val id: Long,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String
)

