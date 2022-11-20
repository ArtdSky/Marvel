package com.example.marvel.presentation.screens.home.slider

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.marvel.data.network.models.Result
import com.example.marvel.presentation.MainViewModel

@Composable
fun CharacterBox(navController: NavHostController, viewModel: MainViewModel) {

    val state by viewModel.viewState.collectAsState()

    val data = viewModel.getAllFromRoom.observeAsState()

    val localData = mutableListOf<Result>()
    data.value?.forEach {
        localData.add(Result(it.id, it.name, it.description, it.thumbnail))
    }
    localData.sortedBy { it.name }
    Log.d("TAG", localData.toString())

    val allCharacters = if(state.gotError){
        localData
    } else{
        state.characters
    }


    Box(
        modifier = Modifier.padding(top = 150.dp)
    ) {
        CharactersList(
            navController = navController,
            viewModel = viewModel,
            characters =  allCharacters
        )
    }
}