package com.example.marvel.presentation

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.marvel.data.network.MarvelApi
import com.example.marvel.data.network.models.Result
import com.example.marvel.presentation.utils.colors
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    init {
        viewModelScope.launch {
            try {
                val listResult = MarvelApi.retrofitService.getCharacters()
                _status.value = listResult.data.results
//                Log.d("TAB", listResult.data.results.toString())
            } catch (e: Exception) {
                Log.d("TABB", e.message.toString())
            }
        }
    }

    private val _status = MutableLiveData<List<Result>>()
    val status: LiveData<List<Result>> = _status


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