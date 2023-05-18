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
import com.uniandes.miso.vinyls.models.Artist

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