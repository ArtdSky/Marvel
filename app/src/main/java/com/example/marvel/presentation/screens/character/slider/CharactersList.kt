package com.example.marvel.presentation.screens.character.slider

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.example.marvel.data.network.models.Result
import com.example.marvel.presentation.MainViewModel
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.SnapOffsets
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun CharactersList(
    navController: NavHostController,
    viewModel: MainViewModel,
    characters: List<Result>
) {


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

        items(items = characters) { character ->
//            Log.d("TAB-LIST", character.toString() )
//
//            if (character.id - 1 == viewModel.snapedItem)
//                CharacterCard(
//                    viewModel = viewModel,
//                    character = character,
//                    enableResize = true,
//                    navController = navController
//                )
//            else
            CharacterCard(
                viewModel = viewModel,
                character = character,
                enableResize = false,
                navController = navController
            )
        }
    }
}