package com.example.marvel.presentation

import androidx.compose.runtime.Composable
import com.example.marvel.presentation.character.CharacterBox

@Composable
fun Home() {
    com.example.marvel.presentation.screens.background.Background()
    com.example.marvel.presentation.screens.logo.Logo()
    CharacterBox()
}