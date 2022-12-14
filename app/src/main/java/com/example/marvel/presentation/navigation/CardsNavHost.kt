package com.example.marvel.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.marvel.presentation.MainViewModel
import com.example.marvel.presentation.screens.detail.CharacterDetail
import com.example.marvel.presentation.screens.home.Home

@Composable
fun CardsNavHost(navController: NavHostController, viewModel: MainViewModel) {

    viewModel.getAllCharacters()
    viewModel.insertCharacters()
    NavHost(
        navController = navController,
        startDestination = NavRoute.MainScreen.route
    ) {
        composable(route = NavRoute.MainScreen.route) {
            Home(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(
            NavRoute.CharacterDetail.route + "/{Id}"

        ) { backStackEntry ->
            CharacterDetail(
                navController = navController,
                characterId = backStackEntry.arguments?.getString("Id"),
                viewModel = viewModel
            )
        }
    }
}

