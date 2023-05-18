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
import com.google.gson.Gson
import com.uniandes.miso.vinyls.models.Album
import com.uniandes.miso.vinyls.models.Collector

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