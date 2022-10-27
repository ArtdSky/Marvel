package com.example.marvel.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.marvel.presentation.screens.character.CharacterBox

@Composable
fun Home(navController: NavHostController) {
    Background()
    Logo()
    CharacterBox(navController = navController)
}