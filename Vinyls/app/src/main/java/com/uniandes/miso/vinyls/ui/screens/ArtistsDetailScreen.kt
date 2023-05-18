package com.uniandes.miso.vinyls.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.uniandes.miso.vinyls.R
import com.uniandes.miso.vinyls.models.Artist
import com.uniandes.miso.vinyls.utils.MainAppBar

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
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Detail Artists Screen name: " + artistDetail.name,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.h4
        )
    }
}