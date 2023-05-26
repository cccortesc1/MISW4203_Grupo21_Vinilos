package com.uniandes.miso.vinyls.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.uniandes.miso.vinyls.viewmodels.AlbumViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uniandes.miso.vinyls.R
import com.uniandes.miso.vinyls.models.Album
import com.uniandes.miso.vinyls.utils.MainAppBar
import com.uniandes.miso.vinyls.utils.User


@Composable
fun AlbumsScreen(
    albumViewModel: AlbumViewModel = hiltViewModel(),
    navController: NavHostController,
    userType: String
) {

    val albumItems = albumViewModel.albums.observeAsState()
    Scaffold(
        topBar = { MainAppBar(navController, R.string.albums) }
    ) { padding ->

        LazyColumn {
            item {
                    NewAlbumButton(
                        userType = userType,
                        navController = navController,
                        name = "Crear Álbum"
                    )
            }

            item {
                Box(
                    modifier = Modifier
                        .height(550.dp)
                        .fillMaxWidth()
                ) {
                    AlbumMediaList(
                        modifier = Modifier.padding(padding),
                        albumItems,
                        navController,
                        userType
                    )

                }

            }
        }
    }
}

@Composable
fun AlbumMediaList(
    modifier: Modifier,
    albumItems: State<List<Album>?>,
    navController: NavHostController,
    userType: String
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(dimensionResource(R.dimen.cell_min_width)),
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_xsmall)),
        modifier = modifier
    ) {
        albumItems.value?.let { listAlbum ->
            items(listAlbum) {
                AlbumMediaListItem(
                    albumItem = it,
                    navController,
                    userType
                )
            }
        }
    }
}

@Composable
fun AlbumMediaListItem(
    albumItem: Album,
    navController: NavHostController,
    userType: String
) {
    Card(
        modifier = Modifier
            .clickable {
                navController.navigate("listado/albums/${albumItem}/${userType}")
            }
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

@Composable
fun NewAlbumButton(
    name: String,
    userType: String,
    navController: NavHostController
) {
    if (userType == User.COLECCCIONISTA.idUser) {


            Button(
                onClick = {
                    navController.navigate("listado/albumes/album-detail/nuevo-album")
                },
                shape = RectangleShape,
                modifier = Modifier.fillMaxWidth().padding(25.dp, 0.dp, 25.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = name)
            }
    }
}