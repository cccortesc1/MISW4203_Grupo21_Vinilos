package com.uniandes.miso.vinyls.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uniandes.miso.vinyls.R
import com.uniandes.miso.vinyls.models.Artist
import com.uniandes.miso.vinyls.utils.MainAppBar
import com.uniandes.miso.vinyls.viewmodels.ArtistViewModel

@Composable
fun ArtistsScreen(
    artistViewModel: ArtistViewModel = hiltViewModel(),
    navController: NavHostController
) {
    //val artistsItems: List<Artist>? by artistViewModel.artists.observeAsState()
    val artists = artistViewModel.artists.observeAsState()

    @Composable
    fun ArtistItem(artistItem: Artist) {

        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = artistItem.image)
                .apply(block = fun ImageRequest.Builder.() {
                    size(200, 200)
                }).build()
        )

        Column(
            modifier = Modifier
                .height(210.dp)
                .background(MaterialTheme.colors.onBackground)
                .fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(160.dp)
                    .background(MaterialTheme.colors.onBackground)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Image(
                    painter = painter,
                    contentDescription = "Imagen del artista ${artistItem.name}",
                    modifier = Modifier.size(160.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(MaterialTheme.colors.onBackground)
                    .padding(4.dp)
            ) {
                Text(
                    text = artistItem.name,
                    color = MaterialTheme.colors.primaryVariant,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    @Composable
    fun MediaListArtistItem(
        artistsItem: Artist
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .height(210.dp),
            elevation = 10.dp
        ) {
            ArtistItem(artistsItem)
        }
    }

    @Composable
    fun MediaListArtists(modifier: Modifier, artistsItems: State<List<Artist>?>) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Adaptive(150.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            artistsItems.value?.let { listArtists ->
                items(listArtists) {
                    MediaListArtistItem(
                        artistsItem = it
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = { MainAppBar(navController, R.string.artists) }
    ) { padding ->
        MediaListArtists(
            modifier = Modifier.padding(padding),
            artists
        )
    }
}

    /*Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Artistas",
                style = MaterialTheme.typography.h4,
                color = Color.Black
            )



                items(artistsItems) { artist ->
                    val painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(data = artist.image)
                            .apply(block = fun ImageRequest.Builder.() {
                                size(200, 200)
                            }).build()
                    )
                    Card(
                        elevation = 4.dp
                        //shape = RoundedCornerShape(20.dp)
                    ) {

                    }
                }
            }
        }
    }
}*/


