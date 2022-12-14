package com.example.marvel.presentation

import androidx.compose.ui.graphics.ImageBitmap
import androidx.palette.graphics.Palette
import com.example.marvel.data.network.models.Result

data class ViewState(
    val gotError: Boolean = false,
    val messageError: String? = null,
    val codeError: String? = null,
    val character: Result? = null,
    val imageBitmap: ImageBitmap? = null,
    val colorPalette: Palette? = null,
    val characters: List<Result>? = null,
)
