package com.uniandes.miso.vinyls.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.uniandes.miso.vinyls.R
import com.uniandes.miso.vinyls.utils.MainAppBar

@Composable
fun DetailArtistsScreen(
    navController: NavHostController,
    context: Context = LocalContext.current
){
    Scaffold(
        topBar = { MainAppBar(navController, R.string.collectors) }
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxSize()
                               .padding(padding),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "Detail Artists Screen",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h4
            )
        }
    }

}