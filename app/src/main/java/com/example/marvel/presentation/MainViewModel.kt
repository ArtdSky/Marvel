package com.example.marvel.presentation

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvel.R
import com.example.marvel.presentation.utils.colors

class MainViewModel(application: Application) : AndroidViewModel(application) {



    var triangleColor by mutableStateOf(colors[0])

    var snapedItem by mutableStateOf(0)

    fun setColor(col: Int?) {
        triangleColor = colors[col!!]
    }
}


class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}