package com.example.marvel.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvel.data.local.model.MarvelCharactersEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {
    @Query("SELECT * from marvel_characters")
    fun getAll(): Flow<List<MarvelCharactersEntity>>

    @Query("SELECT * from marvel_characters where id = :id")
    fun getById(id: Int): MarvelCharactersEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(characters: MarvelCharactersEntity)

    @Query("DELETE FROM marvel_characters")
    suspend fun deleteAllCharacters()
}
