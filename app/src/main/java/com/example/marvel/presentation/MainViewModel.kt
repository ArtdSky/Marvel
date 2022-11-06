package com.example.marvel.presentation

import android.app.Application
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.*
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import com.example.marvel.data.network.MarvelApi
import com.example.marvel.data.network.models.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.URL


class MainViewModel(application: Application) : AndroidViewModel(application) {

    data class ViewState(
        val character: Result? = null,
        val imageBitmap: ImageBitmap? = null,
        val colorPalette: Palette? = null,
        val gotError: String? = null,
        val characters: List<Result>? = null,
    )

    private val _viewState = MutableStateFlow(ViewState())
    val viewState = _viewState.asStateFlow()

    fun getAllCharacters() {
        viewModelScope.launch {
            try {
                val listResult = MarvelApi.retrofitCharactersService.getCharacters()
                _viewState.update { currentState: ViewState ->
                    currentState.copy(
                        characters = listResult.data.results,
                        gotError = null
                    )
                }
            } catch (e: Exception) {
                _viewState.update { currentState: ViewState ->
                    currentState.copy(
                        gotError = e.message.toString(),
                    )
                }
            }
        }
    }

    fun getCharacter(id: String?) {
        viewModelScope.launch {
            try {
                val listResult = MarvelApi.retrofitCharacterService.getCharacter(id)
                _viewState.update { currentState: ViewState ->
                    currentState.copy(
                        character = listResult.data.results[0]
                    )
                }
            } catch (e: Exception) {
                _viewState.update { currentState: ViewState ->
                    currentState.copy(
                        gotError = e.message.toString(),
                    )
                }
            }
        }
    }

    fun setPalette(uri: URL?, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("TAB_VM", uri.toString())
            val imageLoader = ImageLoader.Builder(context).build()
            val request = ImageRequest.Builder(context)
                .data(uri.toString())
                .allowHardware(false)
                .target { result ->
                    val bitmap = (result as BitmapDrawable).bitmap
                    Palette.Builder(bitmap).generate { palettes ->
                        _palette.postValue(palettes)
                    }
                }
                .build()
            imageLoader.enqueue(request)
        }
    }


    fun setImgUrl(uri: URL) {
        _url.postValue(uri)
    }

    private val _url = MutableLiveData<URL?>()
    val url: LiveData<URL?> = _url

    private val _palette = MutableLiveData<Palette?>()
    val palette: LiveData<Palette?> = _palette

    var snappedItem by mutableStateOf(0)

    fun setHeroColor(swatch: Palette.Swatch?): Color {
        var color = Color.Red
        swatch?.rgb?.let {
           color = Color(it)
        }
        return color

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