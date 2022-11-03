package com.example.marvel.presentation.screens.home.slider

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.marvel.presentation.MainViewModel

@Composable
fun CharacterBox(navController: NavHostController, viewModel : MainViewModel) {

    val allCharacters  = viewModel.characters.observeAsState().value

    allCharacters?.let {
        Box(
            modifier = Modifier.padding(top = 150.dp)
        ) {


            CharactersList(navController = navController,
                viewModel = viewModel,
                characters = it

            )
        }
    }

}