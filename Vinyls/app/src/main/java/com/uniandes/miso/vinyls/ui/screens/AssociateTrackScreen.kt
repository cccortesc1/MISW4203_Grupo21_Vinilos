package com.uniandes.miso.vinyls.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.uniandes.miso.vinyls.R
import com.uniandes.miso.vinyls.models.TrackAssociated
import com.uniandes.miso.vinyls.utils.MainAppBar
import com.uniandes.miso.vinyls.viewmodels.AlbumViewModel

@Composable
fun AssociateTrackScreen(
    id: Int,
    navController: NavHostController,
    albumViewModel: AlbumViewModel = hiltViewModel(),
    context: Context = LocalContext.current
) {
    Scaffold(
        topBar = { MainAppBar(navController, R.string.associate_track) }
    ) { padding ->
        AssociateTrack(
            modifier = Modifier.padding(padding),
            id,
            albumViewModel,
            context
        )
    }
}

@Composable
fun AssociateTrack(
    modifier: Modifier,
    idAlbum: Int,
    albumViewModel: AlbumViewModel,
    context: Context
) {

    val isTrackAdded = albumViewModel.associateTrack.observeAsState()
    val isLoadingTrack = albumViewModel.loadingTrack.observeAsState()

    if (isTrackAdded.value == true) {
        Toast.makeText(context, "Track agregada al album", Toast.LENGTH_LONG).show()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val trackName = remember {
            mutableStateOf(TextFieldValue())
        }
        val duration = remember {
            mutableStateOf(TextFieldValue())
        }

        Spacer(modifier = Modifier.height(25.dp))

        OutlinedTextField(
            modifier = modifier
                .padding(15.dp, 0.dp, 15.dp, 0.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.LightGray,
                placeholderColor = Color.Black
            ),
            value = trackName.value,
            onValueChange = { trackName.value = it },
            label = { Text(text = "Nombre") },
            placeholder = { Text(text = "track nombre") },
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            modifier = modifier
                .padding(15.dp, 0.dp, 15.dp, 0.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.LightGray,
                placeholderColor = Color.Black
            ),
            value = duration.value,
            onValueChange = { duration.value = it },
            label = { Text(text = "Duración") },
            placeholder = { Text(text = "5:05") },
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {
                albumViewModel.associateTrack(
                    TrackAssociated(
                        trackName.value.text,
                        duration.value.text
                    ), idAlbum
                )
            },
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 0.dp, 15.dp, 0.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                contentColor = Color.White
            )

        ) {
            Text(text = "Asociar")
        }

        Spacer(modifier = Modifier.height(15.dp))

        if (isLoadingTrack.value == true) {
            CircularProgressIndicator()
        }
    }
}

