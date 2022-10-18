package com.example.marvel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import dev.chrisbanes.snapper.SnapperLayoutItemInfo

class MainViewModel : ViewModel() {

    val colors = listOf(Color.Red, Color.Yellow, Color.Blue, Color.Green)

    var isEnabled by mutableStateOf(false)
    var triangleColor by  mutableStateOf(Color.Yellow)


    fun setColor( col : Int){
        triangleColor = colors[col]
    }
}