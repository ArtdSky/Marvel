package com.example.marvel.data.local

import android.content.Context
import android.util.Log
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.marvel.data.local.model.MarvelCharactersEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [MarvelCharactersEntity::class],
    version = 3,
    autoMigrations = [
        AutoMigration (from = 1, to = 2),
        AutoMigration (from = 2, to = 3)
                     ],
    exportSchema = true)
abstract class MarvelDatabase : RoomDatabase() {

    abstract fun marvelDao(): MarvelDao

    companion object {

        @Volatile
        private var INSTANCE: MarvelDatabase? = null

        fun getDatabase(
            context: Context
        ): MarvelDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MarvelDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}