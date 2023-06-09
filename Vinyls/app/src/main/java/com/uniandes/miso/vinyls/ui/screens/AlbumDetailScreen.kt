package com.uniandes.miso.vinyls.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uniandes.miso.vinyls.R
import com.uniandes.miso.vinyls.models.*
import com.uniandes.miso.vinyls.utils.MainAppBar
import com.uniandes.miso.vinyls.utils.User
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AlbumDetailScreen(
    navController: NavHostController,
    albumDetail: Album,
    userType: String
) {
    Scaffold(
        topBar = { MainAppBar(navController, R.string.albums) }
    ) { padding ->
        AlbumDetail(
            modifier = Modifier.padding(padding),
            albumDetail,
            userType,
            navController
        )
    }
}

@Composable
fun AlbumDetail(modifier: Modifier, albumDetail: Album, userType: String, navController: NavHostController) {

    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = albumDetail.cover)
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
            ) {
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    backgroundColor = Color.White,
                    elevation = 20.dp
                ) {
                    Column(
                        modifier = modifier
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,

                        ) {
                        Text(
                            text = albumDetail.name,
                            color = MaterialTheme.colors.primary,
                            style = typography.h5
                        )

                        Image(
                            painter = painter,
                            contentDescription = "Imagen del álbum ${albumDetail.name}",
                            modifier = Modifier.size(160.dp),
                            contentScale = ContentScale.Crop
                        )

                        TextButton(onClick = { }) {
                            Row(
                                modifier = Modifier
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                val inputFormat =
                                    SimpleDateFormat(
                                        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                                        Locale.getDefault()
                                    )
                                val outputFormat =
                                    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

                                val dateTime: Date =
                                    inputFormat.parse(albumDetail.releaseDate) as Date
                                val formattedDate: String = outputFormat.format(dateTime)
                                Text(formattedDate)
                            }
                        }

                        TextButton(onClick = { }) {
                            Row(
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(albumDetail.description)
                            }
                        }
                    }
                }
            }
        }

        item {
            Box(
            ) {
                Column(
                ) {
                    if (userType == User.COLECCCIONISTA.idUser) {
                        Button(
                            onClick = {
                                navController.navigate("listado/albumes/asociar-track/${albumDetail.albumId}")
                            },
                            shape = RectangleShape,
                            modifier = Modifier.width(width = 300.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Black,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Asociar Track")
                        }
                    }
                }
            }
        }

        item {
            Box(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Column(
                    modifier = modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Tracks",
                        color = MaterialTheme.colors.primary,
                        style = typography.h5,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(vertical = 8.dp)
                    )

                    AlbumTracks(albumDetail = albumDetail)
                }
            }
        }

        item {
            Box(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Column(
                    modifier = modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Performers",
                        color = MaterialTheme.colors.primary,
                        style = typography.h5,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(vertical = 8.dp)

                    )

                    AlbumPerformers(albumDetail = albumDetail)
                }
            }
        }

        item {
            Box(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Column(
                    modifier = modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Comentarios",
                        color = MaterialTheme.colors.primary,
                        style = typography.h5,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(vertical = 8.dp)
                    )

                    AlbumComments(albumDetail.comments)
                }
            }
        }
    }
}


@Composable
fun AlbumComments(comments: List<Comment>) {
    LazyColumn {
        items(comments, itemContent = {
            AlbumCommentItem(comment = it)
        })
    }
}

@Composable
fun AlbumCommentItem(comment: Comment) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 10.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(4.dp))
    ) {
        Row {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
                    .padding(8.dp)
            ) {
                Text(text = comment.description, style = typography.body1, color = Color.Gray)
                Text(
                    text = "rating: ${comment.rating}",
                    style = typography.caption,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun AlbumTracks(albumDetail: Album) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(dimensionResource(R.dimen.cell_min_width)),
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_xsmall)),
    ) {
        items(albumDetail.tracks) {
            TrackItem(
                it
            )
        }
    }
}

@Composable
fun AlbumPerformers(albumDetail: Album) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(dimensionResource(R.dimen.cell_min_width)),
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_xsmall)),
    ) {
        items(albumDetail.performers) {
            PerformerItem(
                it
            )
        }
    }
}

@Composable
fun TrackItem(
    albumTrack: Track
) {
    Card(
        modifier = Modifier
            .height(150.dp),
        elevation = 10.dp
    ) {
        AlbumTrackItem(albumTrack)
    }
}

@Composable
fun PerformerItem(
    albumPerformer: Performer
) {
    Card(
        modifier = Modifier
            .height(150.dp),
        elevation = 10.dp
    ) {
        AlbumPerformerItem(albumPerformer)
    }
}

@Composable
fun AlbumTrackItem(track: Track) {

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
            Column {
                Text(
                    text = "Nombre: " + track.name
                )
                Text(
                    text = "Duración: " + track.duration
                )
            }
        }
    }
}

@Composable
fun AlbumPerformerItem(performer: Performer) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = performer.image)
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
                contentDescription = "Imagen del performer ${performer.name}",
                modifier = Modifier.size(160.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = performer.name,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(4.dp),
                maxLines = 1
            )
        }
    }
}





