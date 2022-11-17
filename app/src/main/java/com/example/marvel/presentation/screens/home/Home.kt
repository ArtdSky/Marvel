package com.example.marvel.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.marvel.presentation.MainViewModel
import com.example.marvel.presentation.screens.home.slider.CharacterBox

@Composable
fun Home(navController: NavHostController, viewModel: MainViewModel) {
    val state by viewModel.viewState.collectAsState()
    val gotError = state.gotError


    MainScreenBackground(viewModel = viewModel)
    if (!gotError) {
        MainScreenLogo()
        CharacterBox(navController = navController, viewModel = viewModel)
    } else {
        Text(
            text = "Sorry but:${state.codeError} ${state.messageError}",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            modifier = Modifier.padding(top = 100.dp)
        )
    }
}