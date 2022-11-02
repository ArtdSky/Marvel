package com.example.marvel.presentation.screens.character.slider

import android.app.Application
import android.util.Log
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.marvel.domain.model.Character
import com.example.marvel.presentation.MainViewModel
import com.example.marvel.presentation.MainViewModelFactory
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.SnapOffsets
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun CharactersList(
    navController: NavHostController,
    viewModel : MainViewModel,
    characters: List<Character>
) {
    val allCharacters  = viewModel.status.observeAsState().value


    allCharacters?.let{
        it.forEach{
            Log.d("TAB-CARD", it.toString() )
        }
    }

    val lazyListState = rememberLazyListState()
    val layoutInfo = rememberLazyListSnapperLayoutInfo(lazyListState)


    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (!lazyListState.isScrollInProgress) {
            val snappedItem = layoutInfo.currentItem

            viewModel.setColor(snappedItem?.index)
            viewModel.snapedItem = snappedItem?.index ?: 0
        }
    }

    LazyRow(
        state = lazyListState,
        flingBehavior = rememberSnapperFlingBehavior(
            lazyListState = lazyListState,
            snapOffsetForItem = SnapOffsets.Center
        )
    ) {

        items(items = characters, key = { it.id }) { character ->
            if (character.id - 1 == viewModel.snapedItem)
                CharacterCard(
                    viewModel = viewModel,
                    character = character,
                    enableResize = true,
                    navController = navController
                )
            else
                CharacterCard(
                    viewModel = viewModel,
                    character = character,
                    enableResize = false,
                    navController = navController
                )
        }
    }
}