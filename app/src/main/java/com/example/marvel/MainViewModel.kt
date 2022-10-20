package com.example.marvel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.ViewModel
import dev.chrisbanes.snapper.SnapperLayoutItemInfo

class MainViewModel : ViewModel() {

    val colors = listOf(
        R.color.burgundy,
        R.color.redBlood,
        R.color.brown,
        R.color.brightBlue,
        R.color.cadetBlue,
        R.color.iris,
        R.color.jade)

    var isEnabled by mutableStateOf(false)
    var triangleColor by  mutableStateOf(colors[0])

    var snapedItem by mutableStateOf(0)

    fun setColor( col : Int?){
        triangleColor = colors[col!!]
    }
}