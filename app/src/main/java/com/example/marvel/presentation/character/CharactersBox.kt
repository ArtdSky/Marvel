package com.example.marvel.presentation.character

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.marvel.data.CharactesData

@Composable
fun CharacterBox() {
    Box(
        modifier = Modifier.padding(top = 150.dp)
    ) {
        CharactersList(characters = CharactesData.charactersData)
    }
}