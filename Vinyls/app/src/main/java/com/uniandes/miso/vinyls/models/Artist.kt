package com.uniandes.miso.vinyls.models

import android.net.Uri
import com.google.gson.Gson


data class Artist(
    val artistId : Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String,
    val albums: List<Album>,
    val prizes: List<Prize>
){
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}

data class Prize(
    val prizeId: Int,
    val premiationDate: String
)
