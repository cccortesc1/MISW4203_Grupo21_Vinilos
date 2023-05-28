package com.uniandes.miso.vinyls.models

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

@Entity(tableName = "collectors_table")
data class Collector(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
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
    @SerializedName("id")
    val id: Long,
    @SerializedName("price")
    val price: Long,
    @SerializedName("status")
    val status: String
)

data class Comment (
    @SerializedName("id")
    val id: Long,
    @SerializedName("description")
    val description: String,
    @SerializedName("rating")
    val rating: Long
)

data class FavoritePerformer (
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("birthDate")
    val birthDate: String
)

