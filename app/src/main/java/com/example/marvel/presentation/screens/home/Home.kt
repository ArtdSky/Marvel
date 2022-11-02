package com.example.marvel.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.marvel.presentation.MainViewModel
import com.example.marvel.presentation.screens.background.Background
import com.example.marvel.presentation.screens.character.slider.CharacterBox
import com.example.marvel.presentation.screens.logo.Logo

@Composable
fun Home(navController: NavHostController, viewModel : MainViewModel) {

    Background( viewModel = viewModel)
    Logo()
    CharacterBox(navController = navController, viewModel = viewModel)
}