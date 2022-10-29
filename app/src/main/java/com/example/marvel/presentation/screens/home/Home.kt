package com.example.marvel.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.marvel.presentation.screens.background.Background
import com.example.marvel.presentation.screens.character.CharacterBox

@Composable
fun Home(navController: NavHostController) {
    Background()
    com.example.marvel.presentation.screens.logo.Logo()
    CharacterBox(navController = navController)
}