package com.uniandes.miso.vinyls.ui.screens

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.uniandes.miso.vinyls.utils.MainAppBar
import com.uniandes.miso.vinyls.viewmodels.AlbumViewModel
import java.util.Calendar
import java.util.Date

@Composable
fun AlbumCreateScreen(
    albumViewModel: AlbumViewModel = hiltViewModel(),
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            MainAppBar(navController, R.string.create_album)
        }
    ) { padding ->
        AlbumCreate(
            modifier = Modifier.padding(padding),
            albumViewModel = albumViewModel
        )
    }
}

@Composable
fun AlbumCreate(modifier: Modifier, albumViewModel: AlbumViewModel) {

    val context = LocalContext.current

    Column (
        modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        // val focusManager = LocalFocusManager.current

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
                onClick = {},
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
            albumViewModel.date.value = "$dayOfMonth/$month/$year"
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
                            // works like onClick
                            datePickerDialog.show()
                        }
                    }
                }
            },
        onValueChange = { albumViewModel.date.value = it },
        placeholder = { Text(text = "dd/mm/yyyy") },
    )

}