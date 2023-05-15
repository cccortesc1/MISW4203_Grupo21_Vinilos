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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
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
import com.uniandes.miso.vinyls.models.Collector
import com.uniandes.miso.vinyls.models.Comment
import com.uniandes.miso.vinyls.models.FavoritePerformer
import com.uniandes.miso.vinyls.utils.MainAppBar

@Composable
fun CollectorDetailScreen(
    navController: NavHostController,
    collectorDetail: Collector
) {
    Scaffold(
        topBar = { MainAppBar(navController, R.string.collectors) }
    ) { padding ->
        CollectorDetail(
            modifier = Modifier.padding(padding),
            collectorDetail
        )
    }
}

@Composable
fun CollectorDetail(modifier: Modifier, collectorDetail: Collector) {
    Log.d("DebugRecomposition", Gson().toJson(collectorDetail))
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
                text = collectorDetail.name,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h5
            )

            TextButton(onClick = { }) {
                Row(
                    modifier = Modifier
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Email"
                    )
                    Text(collectorDetail.email)
                }
            }

            TextButton(onClick = { }) {
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {

                    Icon(
                        imageVector = Icons.Filled.Phone,
                        contentDescription = "Phone"
                    )
                    Text(collectorDetail.telephone)
                }
            }

            Text(
                text = "Artistas Favoritos",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(vertical = 8.dp)

            )

            FavoritePerformers(collectorDetail = collectorDetail)

            Text(
                text = "Comentarios",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(vertical = 8.dp)
            )

            Comments(collectorDetail.comments)

        }
    }
}

@Composable
fun Comments(comments: List<Comment>) {
    LazyColumn(
    ) {
        items(comments, itemContent = {
            CommentItem(comment = it)
        })
    }
}

@Composable
fun CommentItem(comment: Comment) {
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
fun FavoritePerformers(collectorDetail: Collector) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(dimensionResource(R.dimen.cell_min_width)),
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_xsmall)),
    ) {
        items(collectorDetail.favoritePerformers) {
            FavoritePerformerItem(
                it
            )
        }
    }
}

@Composable
fun FavoritePerformerItem(
    favoritePerformer: FavoritePerformer
) {
    Card(
        modifier = Modifier
            .height(150.dp),
        elevation = 10.dp
    ) {
        PerformerItem(favoritePerformer)
    }
}

@Composable
fun PerformerItem(performer: FavoritePerformer) {
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





