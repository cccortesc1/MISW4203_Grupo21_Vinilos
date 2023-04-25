package com.uniandes.miso.vinyls.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.uniandes.miso.vinyls.utils.User

@Composable
fun OptionsScreen(id: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ){
        Text(
            text = "Options Screen for ${User.valueOf(id)}",
            style = MaterialTheme.typography.h3,
            color = Color.Black
        )
    }
}