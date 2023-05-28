package com.uniandes.miso.vinyls.models

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson

@Entity(tableName = "artists")
data class Artist(
    @PrimaryKey(autoGenerate = true)
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
