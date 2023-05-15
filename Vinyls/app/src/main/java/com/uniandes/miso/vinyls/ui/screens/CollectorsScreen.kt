package com.uniandes.miso.vinyls.ui.screens

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.uniandes.miso.vinyls.R
import com.uniandes.miso.vinyls.models.Collector
import com.uniandes.miso.vinyls.utils.MainAppBar
import com.uniandes.miso.vinyls.viewmodels.CollectorViewModel

@Composable
fun CollectorsScreen(
    collectorViewModel: CollectorViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val collectorItems = collectorViewModel.collectors.observeAsState()
    Scaffold(
        topBar = { MainAppBar(navController, R.string.collectors) }
    ) { padding ->
        MediaList(
            modifier = Modifier.padding(padding),
            collectorItems,
            navController
        )
    }
}

@Composable
fun MediaList(
    modifier: Modifier,
    collectorItems: State<List<Collector>?>,
    navController: NavHostController
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(dimensionResource(R.dimen.cell_min_width)),
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_xsmall)),
        modifier = modifier
    ) {
        collectorItems.value?.let { listCollector ->
            items(listCollector) {
                MediaListItem(
                    collectorItem = it,
                    navController
                )
            }
        }
    }
}

@Composable
fun MediaListItem(
    collectorItem: Collector,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .clickable {
                navController.navigate("listado/collector/${collectorItem}")
            }
            .padding(8.dp)
            .height(150.dp),
        elevation = 10.dp
    ) {
        CollectorItem(collectorItem)
    }
}

@Composable
fun CollectorItem(collectorItem: Collector) {

    Column(
        modifier = Modifier
            .padding(15.dp)
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(
            text = collectorItem.name,
            style = MaterialTheme.typography.h6
        )
    }

}

