package com.uniandes.miso.vinyls.utils

import android.os.Bundle
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.uniandes.miso.vinyls.models.*
import java.lang.reflect.Type

enum class User(val idUser: String) {
    COLECCCIONISTA("coleccionista"),
    INVITADO("invitado")
}

@Composable
fun MainAppBar(navController: NavHostController, titleResource: Int) {
    TopAppBar(
        title = { Text(stringResource(id = titleResource)) },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(Icons.Rounded.ArrowBack, "")
            }
        })
}

class ArtistArgType : JsonNavType<Artist>() {
    override fun fromJsonParse(value: String): Artist =
        Gson().fromJson(value, Artist::class.java)

    override fun Artist.getJsonParse(): String = Gson().toJson(this)

}

class AlbumArgType : JsonNavType<Album>() {
    override fun fromJsonParse(value: String): Album =
        Gson().fromJson(value, Album::class.java)

    override fun Album.getJsonParse(): String = Gson().toJson(this)

}

class CollectorArgType : JsonNavType<Collector>() {
    override fun fromJsonParse(value: String): Collector =
        Gson().fromJson(value, Collector::class.java)

    override fun Collector.getJsonParse(): String = Gson().toJson(this)

}

abstract class JsonNavType<T> : NavType<T>(isNullableAllowed = false) {
    abstract fun fromJsonParse(value: String): T
    abstract fun T.getJsonParse(): String

    override fun get(bundle: Bundle, key: String): T? =
        bundle.getString(key)?.let { parseValue(it) }

    override fun parseValue(value: String): T = fromJsonParse(value)

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, value.getJsonParse())
    }
}

interface JsonParser {
    fun <T> fromJson(json: String, type: Type): T?
    fun <T> toJson(obj: T, type: Type): String?
}

class CommentTypeConverter {

    val gson = Gson()

    @TypeConverter
    fun collectorToString(collector: List<Comment>): String {
        return gson.toJson(collector)
    }

    @TypeConverter
    fun stringToCollector(collectorString: String): List<Comment> {
        val objectType = object : TypeToken<List<Comment>>() {}.type
        return gson.fromJson(collectorString , objectType)
    }

}

class FavoritePerformersTypeConverter {

    val gson = Gson()

    @TypeConverter
    fun favoritePerformersToString(favoritePerformers: List<FavoritePerformer>): String {
        return gson.toJson(favoritePerformers)
    }

    @TypeConverter
    fun stringToFavoritePerformers(favoritePerformersString: String): List<FavoritePerformer> {
        val objectType = object : TypeToken<List<FavoritePerformer>>() {}.type
        return gson.fromJson(favoritePerformersString , objectType)
    }

}

class CollectorAlbumsTypeConverter {

    val gson = Gson()

    @TypeConverter
    fun collectorAlbumsToString(collectorAlbums: List<CollectorAlbum>): String {
        return gson.toJson(collectorAlbums)
    }

    @TypeConverter
    fun stringToFavoritePerformers(collectorAlbumsString: String): List<CollectorAlbum> {
        val objectType = object : TypeToken<List<CollectorAlbum>>() {}.type
        return gson.fromJson(collectorAlbumsString , objectType)
    }

}
