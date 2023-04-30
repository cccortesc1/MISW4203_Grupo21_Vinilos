package com.uniandes.miso.vinyls.utils

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.uniandes.miso.vinyls.R

enum class User (val idUser: String){
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