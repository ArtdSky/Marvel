package com.example.marvel.presentation.character

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.marvel.domain.model.Character
import com.example.marvel.presentation.MainViewModel
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.SnapOffsets
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun CharactersList(
    characters: List<Character>,
    model: MainViewModel = viewModel()
) {
    val lazyListState = rememberLazyListState()
    val layoutInfo = rememberLazyListSnapperLayoutInfo(lazyListState)


    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (!lazyListState.isScrollInProgress) {
            val snappedItem = layoutInfo.currentItem

            model.setColor(snappedItem?.index)
            model.snapedItem = snappedItem?.index ?: 0
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
            if (character.id - 1 == model.snapedItem)
                CharacterCard(character = character, enableResize = true)
            else
                CharacterCard(character = character, enableResize = false)
        }
    }
}