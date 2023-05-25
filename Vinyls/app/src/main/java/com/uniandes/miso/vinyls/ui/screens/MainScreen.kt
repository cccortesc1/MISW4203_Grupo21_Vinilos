package com.uniandes.miso.vinyls.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.uniandes.miso.vinyls.R

@Composable
fun MainScreen(navController: NavHostController) {
    // A surface container using the 'background' color from the theme

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) })
        }
    ) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .testTag("mainScreen"),
            color = MaterialTheme.colors.background
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Thumb()
                ButtonWithRectangleShape("Coleccionista", "coleccionista", navController, false)
                ButtonWithRectangleShape("Invitado", "invitado", navController, false)
            }
        }
    }
}

@Composable
fun Thumb() {
    Box(
        modifier = Modifier
            .height(dimensionResource(R.dimen.cell_thumb_height))
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = "https://cdn.pixabay.com/photo/2017/04/19/10/24/vinyl-2241789_1280.png",
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun ButtonWithRectangleShape(
    name: String,
    userId: String,
    navController: NavHostController,
    listado: Boolean
) {
    Button(
        onClick = {
            if (listado) {
                if (name == "Albumes") {
                    navController.navigate("listado/${name.lowercase()}/${userId}")
                } else {
                    navController.navigate("listado/${name.lowercase()}")
                }

            } else {
                navController.navigate("options/$userId")
            }

        },
        shape = RectangleShape,
        modifier = Modifier.width(width = 300.dp)
            .testTag(name),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Black,
            contentColor = Color.White
        )
    ) {
        Text(text = name)
    }
}
