package com.example.marvel.presentation.navigation

sealed class NavRoute(val route: String) {
    object MainScreen : NavRoute("main_screen")
    object CharacterDetail : NavRoute("detail_screen")
}
