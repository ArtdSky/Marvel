package com.example.marvel.presentation.screens.character.slider

import android.app.Application
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.marvel.domain.model.Character
import com.example.marvel.presentation.MainViewModel
import com.example.marvel.presentation.MainViewModelFactory
import com.example.marvel.presentation.screens.character.CharacterCard
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.SnapOffsets
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun CharactersList(
    navController: NavHostController,
    characters: List<Character>
) {
    val context = LocalContext.current
    val mViewModel: MainViewModel = viewModel(
        factory = MainViewModelFactory(context.applicationContext as Application)
    )

    val lazyListState = rememberLazyListState()
    val layoutInfo = rememberLazyListSnapperLayoutInfo(lazyListState)


    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (!lazyListState.isScrollInProgress) {
            val snappedItem = layoutInfo.currentItem

            mViewModel.setColor(snappedItem?.index)
            mViewModel.snapedItem = snappedItem?.index ?: 0
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
            if (character.id - 1 == mViewModel.snapedItem)
                CharacterCard(
                    character = character,
                    enableResize = true,
                    navController = navController
                )
            else
                CharacterCard(
                    character = character,
                    enableResize = false,
                    navController = navController
                )
        }
    }
}