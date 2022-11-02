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


    fun getAllCharacters() {
        viewModelScope.launch() {
            try {
                val listResult = MarvelApi.retrofitCharactersService.getCharacters()
                _characters.value = listResult.data.results
            } catch (e: Exception) {
                Log.d("TAB-VM", e.message.toString())
                _error.value = e.message.toString()
            }
        }
    }

    fun getCharacter(id: String?) {
        viewModelScope.launch() {
            try {
                val listResult = MarvelApi.retrofitCharacterService.getCharacter(id)
                _character.value = listResult.data.results
            } catch (e: Exception) {
                _error.value = e.message.toString()
            }
        }
    }

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _characters = MutableLiveData<List<Result>>()
    val characters: LiveData<List<Result>> = _characters

    private val _character = MutableLiveData<List<Result>>()
    val character: LiveData<List<Result>> = _character

    var snapedItem by mutableStateOf(0)
    var triangleColor by mutableStateOf(colors[0])
    fun setColor(col: Int?) {
        triangleColor = colors.random()
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