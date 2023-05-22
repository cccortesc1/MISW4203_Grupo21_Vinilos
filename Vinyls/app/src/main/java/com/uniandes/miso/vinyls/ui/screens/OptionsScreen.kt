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
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavHostController
import com.uniandes.miso.vinyls.R
import com.uniandes.miso.vinyls.utils.MainAppBar

@Composable
fun OptionsScreen(navController: NavHostController, id: String) {
    Scaffold(
        topBar = { MainAppBar(navController, R.string.app_name) }) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .testTag("optionsScreen"),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                if (id == "coleccionista") {
                    ButtonWithRectangleShape("Albumes", "coleccionista", navController, true)
                    ButtonWithRectangleShape("Artistas", "coleccionista", navController, true)
                } else {
                    ButtonWithRectangleShape("Albumes", "invitado", navController, true)
                    ButtonWithRectangleShape("Artistas", "invitado", navController, true)
                    ButtonWithRectangleShape(
                        "Coleccionistas",
                        "invitado",
                        navController,
                        true
                    )
                }
            }
        }
    }
}