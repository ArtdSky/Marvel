package com.example.marvel.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.marvel.R

class MainViewModel : ViewModel() {

    val colors = listOf(
        R.color.burgundy,
        R.color.redBlood,
        R.color.brown,
        R.color.brightBlue,
        R.color.cadetBlue,
        R.color.iris,
        R.color.jade
    )

    var triangleColor by mutableStateOf(colors[0])

    var snapedItem by mutableStateOf(0)

    fun setColor(col: Int?) {
        triangleColor = colors[col!!]
    }
}