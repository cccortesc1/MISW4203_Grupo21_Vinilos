package com.uniandes.miso.vinyls.ui.screens

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.uniandes.miso.vinyls.R
import com.uniandes.miso.vinyls.models.NewAlbum
import com.uniandes.miso.vinyls.utils.MainAppBar
import com.uniandes.miso.vinyls.viewmodels.AlbumViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun AlbumCreateScreen(
    albumViewModel: AlbumViewModel = hiltViewModel(),
    navController: NavHostController,
    context: Context = LocalContext.current
) {
    Scaffold(
        topBar = {
            MainAppBar(navController, R.string.create_album)
        }
    ) { padding ->
        AlbumCreate(
            modifier = Modifier.padding(padding),
            albumViewModel = albumViewModel,
            context
        )
    }
}

@Composable
fun AlbumCreate(modifier: Modifier, albumViewModel: AlbumViewModel, context: Context) {

    val isCreatedAlbum = albumViewModel.createAlbum.observeAsState()
    //val isLoadingTrack = albumViewModel.loadingTrack.observeAsState()

    if (isCreatedAlbum.value == true) {
        Toast.makeText(context, "Nuevo album generado", Toast.LENGTH_LONG).show()
    }

    Column (
        modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){

            Text(
                text = "Crear Album",
                style = TextStyle(fontSize = 18.sp, color = Color.Black)
            )

            Spacer(
                modifier = Modifier.height(15.dp)
            )

            OutlinedTextField(
                modifier = modifier
                    .padding(15.dp, 0.dp, 15.dp, 0.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.LightGray,
                    placeholderColor = Color.DarkGray,
                    textColor = Color.Black
                ),
                label = {
                    Text(
                        text = "Nombre",
                        style = TextStyle(fontSize = 18.sp, color = Color.Black)
                    )
                },
                maxLines = 1,
                value = albumViewModel.albumName.value,
                onValueChange = { albumViewModel.albumName.value = it },
                placeholder = { Text(text = "Nombre") },
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                modifier = modifier
                    .padding(15.dp, 0.dp, 15.dp, 0.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.LightGray,
                    placeholderColor = Color.DarkGray,
                    textColor = Color.Black
                ),
                label = {
                    Text(
                        text = "Portada",
                        style = TextStyle(fontSize = 18.sp, color = Color.Black)
                    )
                },
                maxLines = 1,
                value = albumViewModel.cover.value,
                onValueChange = { albumViewModel.cover.value = it },
                placeholder = { Text(text = "URL") }
            )

            Spacer(modifier = Modifier.height(15.dp))

            ShowDatePicker(context, modifier, albumViewModel)

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                modifier = modifier
                    .padding(15.dp, 0.dp, 15.dp, 0.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.LightGray,
                    placeholderColor = Color.DarkGray,
                    textColor = Color.Black
                ),
                label = {
                    Text(
                        text = "Descripción",
                        style = TextStyle(fontSize = 18.sp, color = Color.Black)
                    )
                },
                value = albumViewModel.description.value,
                maxLines = 4,
                onValueChange = { albumViewModel.description.value = it },
                placeholder = { Text(text = "Descripción") },
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                modifier = modifier
                    .padding(15.dp, 0.dp, 15.dp, 0.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.LightGray,
                    placeholderColor = Color.DarkGray,
                    textColor = Color.Black
                ),
                label = {
                    Text(
                        text = "Género",
                        style = TextStyle(fontSize = 18.sp, color = Color.Black)
                    )
                },
                maxLines = 1,
                value = albumViewModel.genre.value,
                onValueChange = { albumViewModel.genre.value = it },
                placeholder = { Text(text = "Género") },
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                modifier = modifier
                    .padding(15.dp, 0.dp, 15.dp, 0.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.LightGray,
                    placeholderColor = Color.DarkGray,
                    textColor = Color.Black
                ),
                label = {
                    Text(
                        text = "Sello Discográfico",
                        style = TextStyle(fontSize = 18.sp, color = Color.Black)
                    )
                },
                maxLines = 1,
                value = albumViewModel.recordLabel.value,
                onValueChange = { albumViewModel.recordLabel.value = it },
                placeholder = { Text(text = "Sello Discográfico") },
            )

            Spacer(modifier = Modifier.height(15.dp))

        Box(modifier = Modifier.padding(15.dp, 0.dp, 15.dp, 0.dp)) {
            Button(
                onClick = {
                    albumViewModel.createNewAlbum(
                        NewAlbum(
                            name = albumViewModel.albumName.value.text,
                            cover = albumViewModel.cover.value.text,
                            description = albumViewModel.description.value.text,
                            genre = albumViewModel.genre.value.text,
                            recordLabel = albumViewModel.recordLabel.value.text,
                            releaseDate = albumViewModel.date.value
                        )
                    )
                },
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Crear Album")
            }
        }

        Spacer(modifier = Modifier.height(15.dp))
    }
}

@Composable
fun ShowDatePicker(context: Context, modifier: Modifier, albumViewModel: AlbumViewModel)
{
    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        {_: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            albumViewModel.date.value = convertDateString("$dayOfMonth-$month-$year")
        }, year, month, day
    )

    OutlinedTextField(
        modifier = modifier
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.LightGray,
            placeholderColor = Color.DarkGray,
            textColor = Color.Black
        ),
        readOnly = true,
        label = {
            Text(
                text = "Fecha de lanzamiento",
                style = TextStyle(fontSize = 18.sp, color = Color.Black)
            )
        },
        maxLines = 1,
        value = albumViewModel.date.value,
        interactionSource = remember { MutableInteractionSource() }
            .also { interactionSource ->
                LaunchedEffect(interactionSource) {
                    interactionSource.interactions.collect {
                        if (it is PressInteraction.Release) {
                            datePickerDialog.show()
                        }
                    }
                }
            },
        onValueChange = { albumViewModel.date.value = convertDateString(it) },
        placeholder = { Text(text = "dd-mm-yyyy") },
    )

}

fun convertDateString(dateInput: String) : String
{
    val inputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

    val dateTime: Date = inputFormat.parse(dateInput) as Date
    return outputFormat.format(dateTime)
}