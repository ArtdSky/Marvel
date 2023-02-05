package com.example.marvel.presentation

import android.app.Application
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.*
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import com.example.marvel.data.local.MarvelRepository
import com.example.marvel.data.local.model.MarvelCharactersEntity
import com.example.marvel.data.network.MarvelApiService
import com.example.marvel.data.network.models.ErrorResponse
import com.skydoves.retrofit.adapters.result.onFailureSuspend
import com.skydoves.retrofit.adapters.result.onSuccessSuspend
import com.skydoves.retrofit.adapters.serialization.deserializeHttpError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val repository: MarvelRepository,
    private val marvelApiService : MarvelApiService
    ) :  AndroidViewModel(application) {

    init {
        deleteAllFromRepository()
    }


    private val _viewState = MutableStateFlow(ViewState())
    val viewState = _viewState.asStateFlow()

    private fun insertCharacters() {
        viewModelScope.launch {
            viewState.value.characters?.forEach {
                repository.insert(
                    MarvelCharactersEntity(
                        id = it.id,
                        name = it.name,
                        description = it.description,
                        thumbnail = it.thumbnail
                        )
                )
            }
        }
    }

    val getAllFromRoom : LiveData<List<MarvelCharactersEntity>> = repository.getAll.asLiveData()

    private fun deleteAllFromRepository(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    fun getAllCharacters() {
        viewModelScope.launch {
            try {
                val result = marvelApiService.getCharacters()
                result.onSuccessSuspend {
                    _viewState.update { currentState: ViewState ->
                        currentState.copy(
                            characters = it.data.results,
                            gotError = false
                        )
                    }
                    insertCharacters()
                }.onFailureSuspend {
                    val errorBody = it.deserializeHttpError<ErrorResponse>()
                    errorBody?.let {
                        _viewState.update { currentState: ViewState ->
                            currentState.copy(
                                gotError = true,
                                codeError = it.codeError,
                                messageError = it.messageError
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                _viewState.update { currentState: ViewState ->
                    currentState.copy(
                        gotError = true,
                        codeError = "no internet",
                        messageError = e.message.toString(),
                    )
                }
            }
        }
    }

    fun getCharacter(id: String?) {
        viewModelScope.launch {
            try {
                val result = marvelApiService.getCharacter(id)
                result.onSuccessSuspend {
                    _viewState.update { currentState: ViewState ->
                        currentState.copy(
                            character = it.data.results[0]
                        )
                    }

                }.onFailureSuspend {
                    val errorBody = it.deserializeHttpError<ErrorResponse>()
                    errorBody?.let {
                        _viewState.update { currentState: ViewState ->
                            currentState.copy(
                                gotError = true,
                                codeError = it.codeError,
                                messageError = it.messageError
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                _viewState.update { currentState: ViewState ->
                    currentState.copy(
                        gotError = true,
                        codeError = "Unknown error",
                        messageError = e.message.toString(),
                    )
                }
            }
        }
    }

    fun setPalette(uri: URL?, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
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


    fun setHeroBgColor(swatch: Palette.Swatch?): Color {
        var color = Color.Red
        swatch?.rgb?.let {
            color = Color(it)
        }
        return color

    }
}


//class MainViewModelFactory(
//    private val application: Application,
//    private val repository: MarvelRepository
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
//            return MainViewModel(application = application, repository = repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel Class")
//    }
//}