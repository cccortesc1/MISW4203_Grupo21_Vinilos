package com.uniandes.miso.vinyls.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.uniandes.miso.vinyls.R
import com.uniandes.miso.vinyls.models.Collector
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
    Column(
        modifier = modifier
            .padding(8.dp)
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(
            text = collectorDetail.name,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.h6
        )

        Text(
            text = collectorDetail.collectorAlbums.size.toString(),
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.h6
        )
    }
}





