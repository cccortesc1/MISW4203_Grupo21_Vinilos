package com.uniandes.miso.vinyls.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.gson.Gson
import com.uniandes.miso.vinyls.R
import com.uniandes.miso.vinyls.models.Album
import com.uniandes.miso.vinyls.models.Artist
import com.uniandes.miso.vinyls.utils.MainAppBar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DetailArtistsScreen(
    navController: NavHostController,
    artistDetail: Artist
){
    Scaffold(
        topBar = { MainAppBar(navController, R.string.artists) }
    ) { padding ->
        ArtistDetail(
            modifier = Modifier.padding(padding),
            artistDetail
        )
    }
}

@Composable
fun ArtistDetail(modifier: Modifier, artistDetail: Artist) {
    Log.d("DebugRecomposition", Gson().toJson(artistDetail))
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = artistDetail.image)
            .apply(block = fun ImageRequest.Builder.() {
                size(200, 200)
            }).build()
    )

    LazyColumn (
        modifier = modifier
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ){
                Card(
                    modifier = Modifier
                        .padding(16.dp),
                    backgroundColor = Color.White,
                    elevation = 20.dp
                ){
                    Column(
                        modifier = modifier
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,

                        ) {
                        Text(
                            text = artistDetail.name,
                            color = MaterialTheme.colors.primary,
                            style = MaterialTheme.typography.h5
                        )

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .height(170.dp)
                                .fillMaxWidth()
                                .padding(4.dp)
                        ) {
                            Image(
                                painter = painter,
                                contentDescription = "Image of an artist ${artistDetail.name}",
                                modifier = Modifier.size(170.dp),
                                contentScale = ContentScale.Crop
                            )
                        }

                        TextButton(onClick = { }) {
                            Row(
                                modifier = Modifier
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                                val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

                                val dateTime: Date = inputFormat.parse(artistDetail.birthDate) as Date
                                val formattedDate: String = dateTime.let { outputFormat.format(it) }
                                Text(formattedDate)
                            }
                        }

                        TextButton(onClick = { }) {
                            Row(
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(artistDetail.description)
                            }
                        }
                    }
                }
            }
        }

        item{
            Box(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .padding(4.dp)
            ){
                Column(
                    modifier = modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                )
                {
                    Text(
                        text = "Discografía",
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(vertical = 8.dp)
                    )

                    ArtistAlbums(artistDetail = artistDetail)
                }
            }
        }
    }

}

@Composable
fun ArtistAlbums(artistDetail: Artist) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(dimensionResource(R.dimen.cell_min_width)),
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_xsmall)),
    ) {
        items(artistDetail.albums) {
            ArtistAlbumsItem(
                it
            )
        }
    }
}

@Composable
fun ArtistAlbumsItem(album: Album) {
    Card(
        modifier = Modifier
            .height(150.dp),
        elevation = 10.dp
    ) {
        AlbumsItem(album)
    }
}

@Composable
fun AlbumsItem(album: Album) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = album.cover)
            .apply(block = fun ImageRequest.Builder.() {
                size(200, 200)
            }).build()
    )

    Column(
        modifier = Modifier
            .height(210.dp)
            .fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = "Image of an album ${album.name}",
                modifier = Modifier.size(160.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = album.name,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(4.dp),
                maxLines = 1
            )
        }
    }
}
