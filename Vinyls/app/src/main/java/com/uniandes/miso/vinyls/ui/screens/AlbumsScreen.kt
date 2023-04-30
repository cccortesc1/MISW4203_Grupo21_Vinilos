package com.uniandes.miso.vinyls.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.uniandes.miso.vinyls.viewmodels.AlbumViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uniandes.miso.vinyls.R
import com.uniandes.miso.vinyls.models.Album
import com.uniandes.miso.vinyls.models.Collector
import com.uniandes.miso.vinyls.utils.MainAppBar


@Composable
fun AlbumsScreen(
    albumViewModel: AlbumViewModel = hiltViewModel(),
    navController: NavHostController) {

    val albumItems = albumViewModel.albums.observeAsState()
    Scaffold(
        topBar = { MainAppBar(navController, R.string.albums) }
    ) { padding ->
        AlbumMediaList(
            modifier = Modifier.padding(padding),
            albumItems
        )
    }
}

@Composable
fun AlbumMediaList(modifier: Modifier, albumItems: State<List<Album>?>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(dimensionResource(R.dimen.cell_min_width)),
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_xsmall)),
        modifier = modifier
    ) {
        albumItems.value?.let { listAlbum ->
            items(listAlbum) {
                AlbumMediaListItem(
                    albumItem = it
                )
            }
        }
    }
}

@Composable
fun AlbumMediaListItem(
    albumItem: Album
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(150.dp),
        elevation = 10.dp
    ) {
        AlbumItem(albumItem)
    }
}

@Composable
fun AlbumItem(albumItem: Album) {

    Column(
        modifier = Modifier
            .padding(15.dp)
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = albumItem.cover)
                .apply(block = fun ImageRequest.Builder.() {
                    size(200, 200)
                }).build()
        )

        // Muestra la imagen con el painter
        Image(
            painter = painter,
            contentDescription = "Imagen del album ${albumItem.name}",
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop
        )
    }
}