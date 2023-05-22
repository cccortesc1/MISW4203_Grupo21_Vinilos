package com.uniandes.miso.vinyls

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.gson.Gson
import com.uniandes.miso.vinyls.models.Album
import com.uniandes.miso.vinyls.models.Collector
import com.uniandes.miso.vinyls.ui.screens.*
import com.uniandes.miso.vinyls.ui.theme.VinylsTheme
import com.uniandes.miso.vinyls.utils.AlbumArgType
import com.uniandes.miso.vinyls.utils.CollectorArgType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VinylsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainView()
                }
            }
        }
    }
}

@Composable
fun MainView() {
    StatusBarColorEffect()
    //Necesitamos configurar Navigation Compose para poder utilizarlo
    //NavHost nos va a permitir realizar la navegacion
    //Para que no se nos duplique tenemos una funcion rememmber
    val navController = rememberNavController()
    //NavHost es el componente de NavigationCompose que va a definir el grafo
    //de navegación, en ese NavHost le vamos a decir que NavAppController tiene
    //que usar y tambien la pantalla de origen y a las pantallas que se
    //puede navegar, este NavHost es a la vez un composable que va a ser
    //el encargado de pintar el composable correspondiente en funcion del
    //estado en el que nos encontremos
    NavHost(navController = navController, startDestination = "main") {
        //definimos las pantallas/composables. definimos composable y dentro indicamos cual
        //es el composable que queremos que nos pinte cuando estemos en esa ruta
        composable("main") {
            MainScreen(navController)
        }
        composable(
            route = "options/{userId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("userId")
            requireNotNull(id)
            OptionsScreen(navController, id)
        }

        composable(
            route = "listado/albumes/{userId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("userId")
            requireNotNull(id)
            AlbumsScreen(navController = navController, userType = id)
        }

        composable("listado/artistas") {
            ArtistsScreen(navController = navController)
        }

        composable("listado/coleccionistas") {
            CollectorsScreen(navController = navController)
        }

        composable(
            route="listado/albums/{albumItem}/{userType}",
            arguments = listOf(
                navArgument("albumItem"){ type = AlbumArgType() },
                navArgument("userType"){ type = NavType.StringType },
            )
        ) {navBackStackEntry ->
            val albumDetail = navBackStackEntry.arguments?.getString("albumItem")?.let { Gson().fromJson(it, Album::class.java) }
            val userType = navBackStackEntry.arguments?.getString("userType")
            requireNotNull(albumDetail)
            requireNotNull(userType)
            AlbumDetailScreen(navController, albumDetail, userType)
        }

        composable(
            route="listado/collector/{collectorItem}",
            arguments = listOf(navArgument("collectorItem"){
                type = CollectorArgType()
            })
        ) { navBackStackEntry->
            val collectorDetail = navBackStackEntry.arguments?.getString("collectorItem")?.let { Gson().fromJson(it, Collector::class.java) }
            requireNotNull(collectorDetail)
            CollectorDetailScreen(navController, collectorDetail)
        }
    }
}


@Composable
fun StatusBarColorEffect(
    color: Color = MaterialTheme.colors.primaryVariant,
    systemUiController: SystemUiController = rememberSystemUiController()
) {
    // SideEffect: cada vez que haya una recomposicion de nuestro componente se actualizaran  las barras de ui
    SideEffect {
        systemUiController.setSystemBarsColor(color = color)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VinylsTheme {
        MainView()
    }
}