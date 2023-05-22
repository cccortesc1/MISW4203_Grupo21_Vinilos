package com.uniandes.miso.vinyls.ui.screens

import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.uniandes.miso.vinyls.R
import com.uniandes.miso.vinyls.models.Album
import com.uniandes.miso.vinyls.utils.MainAppBar
import com.uniandes.miso.vinyls.viewmodels.AlbumViewModel

@Composable
fun AlbumCreateScreen(
    albumViewModel: AlbumViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val newAlbum = albumViewModel.album.observeAsState()

    Scaffold(
        topBar = { MainAppBar(navController, R.string.albums) }
    ) { padding ->
        AlbumCreate(
            modifier = Modifier.padding(padding),
            newAlbum
        )
    }
}

@Composable
fun AlbumCreate(modifier: Modifier, album: State<Album?>) {

    Column{
        val focusManager = LocalFocusManager.current

        album.value?.let { albumItem ->
            AppTextField(
                text = albumItem.name,
                placeholder = "Nombre del Album",
                /*onChange = {
                    viewModel.firstName = it
                },*/
                imeAction = ImeAction.Next,//Show next as IME button
                keyboardType = KeyboardType.Text, //Plain text keyboard
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )

            AppTextField(
                text = albumItem.cover,
                placeholder = "Url Portada del Album",
                /*onChange = {
                    viewModel.firstName = it
                },*/
                imeAction = ImeAction.Next,//Show next as IME button
                keyboardType = KeyboardType.Text, //Plain text keyboard
                keyBoardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )


        }
    }
}

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    onChange: (String) -> Unit = {},
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyBoardActions: KeyboardActions = KeyboardActions(),
    isEnabled: Boolean = true
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = text,
        onValueChange = onChange,
        leadingIcon = leadingIcon,
        textStyle = TextStyle(fontSize = 18.sp),
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        keyboardActions = keyBoardActions,
        enabled = isEnabled,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Gray,
            disabledBorderColor = Color.Gray,
            disabledTextColor = Color.Black
        ),
        placeholder = {
            Text(text = placeholder, style = TextStyle(fontSize = 18.sp, color = Color.LightGray))
        }
    )
}