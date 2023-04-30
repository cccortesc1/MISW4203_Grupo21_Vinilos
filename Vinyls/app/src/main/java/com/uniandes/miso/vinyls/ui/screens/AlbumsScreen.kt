package com.uniandes.miso.vinyls.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.uniandes.miso.vinyls.viewmodels.AlbumViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest


@Composable
fun AlbumsScreen(
    albumViewModel: AlbumViewModel = hiltViewModel(),
    navController: NavHostController) {

    val albums by albumViewModel.albums.observeAsState(emptyList())

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Álbumes",
                style = MaterialTheme.typography.h4,
                color = Color.Black
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {

                items(albums) { album ->
                    // carga la imagen desde la url en un painter
                    val painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(data = album.cover)
                            .apply(block = fun ImageRequest.Builder.() {
                                size(200, 200)
                            }).build()
                    )

                    // Muestra la imagen con el painter
                    Image(
                        painter = painter,
                        contentDescription = "Imagen del album ${album.name}",
                        modifier = Modifier.size(200.dp),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = album.name,
                        style = MaterialTheme.typography.h6,
                        color = Color.Black
                    )
                }
            }
        }
    }
}