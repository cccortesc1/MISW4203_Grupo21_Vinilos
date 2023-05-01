package com.uniandes.miso.vinyls.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.uniandes.miso.vinyls.R
import com.uniandes.miso.vinyls.utils.MainAppBar
import com.uniandes.miso.vinyls.utils.User

@Composable
fun OptionsScreen(navController: NavHostController, id: String) {
    Scaffold(
        topBar = { MainAppBar(navController, R.string.app_name) }) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                if (User.valueOf(id) == User.COLECCCIONISTA) {
                    ButtonWithRectangleShape("Albumes", User.valueOf(id), navController, true)
                    ButtonWithRectangleShape("Artistas", User.valueOf(id), navController, true)
                } else {
                    ButtonWithRectangleShape("Albumes", User.valueOf(id), navController, true)
                    ButtonWithRectangleShape("Artistas", User.valueOf(id), navController, true)
                    ButtonWithRectangleShape(
                        "Coleccionistas",
                        User.valueOf(id),
                        navController,
                        true
                    )
                }
            }
        }
    }
}