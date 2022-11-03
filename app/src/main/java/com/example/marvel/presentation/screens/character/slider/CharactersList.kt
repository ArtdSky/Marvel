package com.example.marvel.presentation.screens.character.slider

import android.graphics.BitmapFactory
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.marvel.data.network.models.Result
import com.example.marvel.presentation.MainViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharactersList(
    navController: NavHostController,
    viewModel: MainViewModel,
    characters: List<Result>
) {

    val state = rememberLazyListState()
    val snappingLayout = remember(state) { SnapLayoutInfoProvider(state) }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)

    LaunchedEffect(state.isScrollInProgress) {
        if (!state.isScrollInProgress) {
            state.layoutInfo.visibleItemsInfo.forEach { item ->
                viewModel.snapedItem = item.index
            }

        }

    }


    LazyRow(
        state = state,
        verticalAlignment = Alignment.CenterVertically,
        flingBehavior = flingBehavior,
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(items = characters) { index, character ->
            if (index + 1  == viewModel.snapedItem)
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