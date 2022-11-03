package com.example.marvel.presentation.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.example.marvel.presentation.screens.background.MainScreenBackground
import com.example.marvel.presentation.screens.character.slider.CharacterBox
import com.example.marvel.presentation.screens.logo.Logo

@Composable
fun Home(navController: NavHostController, viewModel : MainViewModel) {

    MainScreenBackground( viewModel = viewModel)
    if(viewModel.error.observeAsState().value == null){
        Logo()
        CharacterBox(navController = navController, viewModel = viewModel)
    } else{
        Text(
            text = "Sorry but: ${viewModel.error.observeAsState().value.toString()}",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            modifier = Modifier.padding(top = 100.dp)
        )
    }


}