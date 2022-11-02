package com.example.marvel.presentation.screens.character.slider

import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.marvel.data.CharactesData
import com.example.marvel.presentation.MainViewModel
import com.example.marvel.presentation.MainViewModelFactory

@Composable
fun CharacterBox(navController: NavHostController, viewModel : MainViewModel) {
    Box(
        modifier = Modifier.padding(top = 150.dp)
    ) {

        CharactersList(
            characters = CharactesData.charactersData,
            navController = navController,
            viewModel = viewModel
        )
    }
}