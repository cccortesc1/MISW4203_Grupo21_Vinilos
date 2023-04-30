package com.uniandes.miso.vinyls.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import androidx.compose.runtime.livedata.observeAsState
import com.uniandes.miso.vinyls.viewmodels.ArtistViewModel

@Composable
fun ArtistsScreen(
    artistViewModel: ArtistViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val list by artistViewModel.artists.observeAsState(emptyList())

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
                text = "Artistas",
                style = MaterialTheme.typography.h4,
                color = Color.Black
            )

            LazyVerticalGrid(
                //modifier = Modifier.fillMaxSize(),
                columns = GridCells.Adaptive(150.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {

                items(list) { artist ->
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
                        Column(modifier = Modifier
                            //.border(BorderStroke(1.dp, Color.Black),shape = RoundedCornerShape(15.dp))
                            .height(255.dp)
                            .background(MaterialTheme.colors.onBackground)
                            .fillMaxWidth()){
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .height(200.dp)
                                    .background(MaterialTheme.colors.onBackground)
                                    .fillMaxWidth()
                                    .padding(4.dp)
                            ) {
                                Image(
                                    painter = painter,
                                    contentDescription = "Imagen del artista ${artist.name}",
                                    modifier = Modifier.size(200.dp),
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
                                    text = artist.name,
                                    style = MaterialTheme.typography.h6,
                                    color = MaterialTheme.colors.primaryVariant,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}