package com.uniandes.miso.vinyls.ui.screens

import android.util.Log
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.gson.Gson
import com.uniandes.miso.vinyls.R
import com.uniandes.miso.vinyls.models.*
import com.uniandes.miso.vinyls.utils.MainAppBar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun AlbumDetailScreen(
    navController: NavHostController,
    albumDetail: Album
) {
    Scaffold(
        topBar = { MainAppBar(navController, R.string.albums) }
    ) { padding ->
        AlbumDetail(
            modifier = Modifier.padding(padding),
            albumDetail
        )
    }
}

@Composable
fun AlbumDetail(modifier: Modifier, albumDetail: Album) {
    Log.d("DebugRecomposition", Gson().toJson(albumDetail))
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = albumDetail.cover)
            .apply(block = fun ImageRequest.Builder.() {
                size(200, 200)
            }).build()
    )
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
                style = MaterialTheme.typography.h5
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
                    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

                    val dateTime: LocalDateTime = LocalDateTime.parse(albumDetail.releaseDate, inputFormatter)
                    val formattedDate: String = dateTime.format(outputFormatter)
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

            Text(
                text = "Performers",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(vertical = 8.dp)

            )

            albumPerformers(albumDetail = albumDetail)

            Text(
                text = "Comentarios",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(vertical = 8.dp)
            )

            Comments(albumDetail.comments)

        }
    }
}

@Composable
fun albumComments(comments: List<Comment>) {
    LazyColumn(
    ) {
        items(comments, itemContent = {
            CommentItem(comment = it)
        })
    }
}

@Composable
fun albumCommentItem(comment: Comment) {
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
fun albumPerformers(albumDetail: Album) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(dimensionResource(R.dimen.cell_min_width)),
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_xsmall)),
    ) {
        items(albumDetail.performers) {
            performerItem(
                it
            )
        }
    }
}

@Composable
fun performerItem(
    albumPerformer: Performer
) {
    Card(
        modifier = Modifier
            .height(150.dp),
        elevation = 10.dp
    ) {
        albumPerformerItem(albumPerformer)
    }
}

@Composable
fun albumPerformerItem(performer: Performer) {
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





