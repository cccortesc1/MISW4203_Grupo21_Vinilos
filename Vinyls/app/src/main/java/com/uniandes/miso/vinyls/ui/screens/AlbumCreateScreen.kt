package com.uniandes.miso.vinyls.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.uniandes.miso.vinyls.R
import com.uniandes.miso.vinyls.utils.MainAppBar

@Composable
fun AlbumCreateScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.create_album)) })
        }
    ) { padding ->
        AlbumCreate(
            modifier = Modifier.padding(padding),
        )
    }
}

@Composable
fun AlbumCreate(modifier: Modifier) {

    Column{
        // val focusManager = LocalFocusManager.current

        val name = remember {
            mutableStateOf(TextFieldValue())
        }
        val cover = remember {
            mutableStateOf(TextFieldValue())
        }

        val releaseDate = remember {
            mutableStateOf(TextFieldValue())
        }

        val description = remember {
            mutableStateOf(TextFieldValue())
        }

        val genre = remember {
            mutableStateOf(TextFieldValue())
        }

        val recordLabel = remember {
            mutableStateOf(TextFieldValue())
        }

        Text(
            text = "Crear Album",
            style = TextStyle(fontSize = 18.sp, color = Color.Black)
        )

        Spacer(
            modifier = Modifier.height(15.dp)
        )


        TextField(
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.LightGray, placeholderColor = Color.Black),
            label = { Text(text = "Nombre", style = TextStyle(fontSize = 18.sp, color = Color.Black)) },
            value = name.value,
            onValueChange = { name.value = it },
            placeholder = { Text(text = "Nombre") },
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.LightGray, placeholderColor = Color.Black),
            label = { Text(text = "Portada", style = TextStyle(fontSize = 18.sp, color = Color.Black)) },
            value = cover.value,
            onValueChange = { cover.value = it },
            placeholder = { Text(text = "Portada") },
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.LightGray, placeholderColor = Color.Black),
            label = { Text(text = "Fecha de lanzamiento", style = TextStyle(fontSize = 18.sp, color = Color.Black)) },
            value = releaseDate.value,
            onValueChange = { releaseDate.value = it },
            placeholder = { Text(text = "Fecha de lanzamiento") },
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.LightGray, placeholderColor = Color.Black),
            label = { Text(text = "Descripción", style = TextStyle(fontSize = 18.sp, color = Color.Black)) },
            value = description.value,
            onValueChange = { description.value = it },
            placeholder = { Text(text = "Descripción") },
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.LightGray, placeholderColor = Color.Black),
            label = { Text(text = "Género", style = TextStyle(fontSize = 18.sp, color = Color.Black)) },
            value = genre.value,
            onValueChange = { genre.value = it },
            placeholder = { Text(text = "Género") },
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.LightGray, placeholderColor = Color.Black),
            label = { Text(text = "Sello Discográfico", style = TextStyle(fontSize = 18.sp, color = Color.Black)) },
            value = recordLabel.value,
            onValueChange = { recordLabel.value = it },
            placeholder = { Text(text = "Sello Discográfico") },
        )

        Spacer(modifier = Modifier.height(15.dp))


        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {},
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Crear Album")
            }
        }

        Spacer(modifier = Modifier.height(15.dp))
    }
}