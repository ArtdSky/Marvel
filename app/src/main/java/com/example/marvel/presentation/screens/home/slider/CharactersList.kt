package com.example.marvel.presentation.screens.home.slider

import android.util.Log
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
    characters: List<Result>?
) {

    val stateLazyList = rememberLazyListState()
    val snappingLayout = remember(stateLazyList) { SnapLayoutInfoProvider(stateLazyList) }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)

    LaunchedEffect(stateLazyList.isScrollInProgress) {
        if (!stateLazyList.isScrollInProgress) {
            stateLazyList.layoutInfo.visibleItemsInfo.forEach { item ->
                viewModel.snappedItem = item.index
            }
        }
    }

    LazyRow(
        state = stateLazyList,
        verticalAlignment = Alignment.CenterVertically,
        flingBehavior = flingBehavior,
        modifier = Modifier.fillMaxSize()
    ) {
        characters?.let {
            itemsIndexed(items = it) { index, character ->
                if (index +1 == viewModel.snappedItem){
//                    Log.d("TAG-LIST", "${index + 1} == ${viewModel.snappedItem}".toString())
                    CharacterCard(
                        viewModel = viewModel,
                        character = character,
                        enableResize = true,
                        navController = navController
                    )}
                else{
                    CharacterCard(
                        viewModel = viewModel,
                        character = character,
                        enableResize = false,
                        navController = navController
                    )}
            }
        }
    }
}