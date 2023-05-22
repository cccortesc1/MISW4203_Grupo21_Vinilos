package com.uniandes.miso.vinyls.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uniandes.miso.vinyls.R

@Composable
fun AssociateTrackScreen(
    idAlbum: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.associate_track)) })
        }
    ) { padding ->
        AssociateTrack(
            modifier = Modifier.padding(padding),
            idAlbum
        )
    }
}

@Composable
fun AssociateTrack(modifier: Modifier, idAlbum: String) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val trackName = remember {
            mutableStateOf(TextFieldValue())
        }
        val duration = remember {
            mutableStateOf(TextFieldValue())
        }

        Text(
            text = "Asociar Track",
            style = TextStyle(fontSize = 18.sp, color = Color.Black)
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.LightGray, placeholderColor = Color.Black),
            label = { Text(text = "Nombre", style = TextStyle(fontSize = 18.sp, color = Color.Black)) },
            value = trackName.value,
            onValueChange = { trackName.value = it },
            placeholder = { Text(text = "Nombre") },

            )

        Spacer(modifier = Modifier.height(15.dp))


        TextField(
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.LightGray, placeholderColor = Color.Black),
            label = { Text(text = "Duración", style = TextStyle(fontSize = 18.sp, color = Color.Black)) },
            value = duration.value,
            onValueChange = { duration.value = it },
            placeholder = { Text(text = "Duración") },

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
                Text(text = "Asociar")
            }
        }

        Spacer(modifier = Modifier.height(15.dp))
    }
}

