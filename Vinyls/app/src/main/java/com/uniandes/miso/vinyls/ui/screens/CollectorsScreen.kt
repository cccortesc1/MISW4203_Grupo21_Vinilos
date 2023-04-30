package com.uniandes.miso.vinyls.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.uniandes.miso.vinyls.models.Collector
import com.uniandes.miso.vinyls.viewmodels.CollectorViewModel

@Composable
fun CollectorsScreen(
    collectorViewModel: CollectorViewModel = hiltViewModel(),
    navController: NavHostController
) {

    var list = mutableListOf<Collector>()

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
                text = "Coleccionistas",
                style = MaterialTheme.typography.h4,
                color = Color.Black
            )

            collectorViewModel.collectors.observeForever {
                it.apply {
                    list.addAll(it)
                }
            }

            Text(
                //text = list.joinToString(" "),
                text = list.size.toString(),
                style = MaterialTheme.typography.h4,
                color = Color.Black
            )

        }
    }

}