package com.example.marvel.data.local

import androidx.annotation.WorkerThread
import com.example.marvel.data.local.model.MarvelCharactersEntity
import kotlinx.coroutines.flow.Flow

class MarvelRepository(private val marvelDao: MarvelDao) {

    val getAll : Flow<List<MarvelCharactersEntity>> = marvelDao.getAll()

    suspend fun deleteAll(){
        marvelDao.deleteAllCharacters()
    }

    @Suppress("RedundantSuspendedModifier")
    @WorkerThread
    suspend fun insert(characters: MarvelCharactersEntity){
        marvelDao.insert(characters)
    }

}