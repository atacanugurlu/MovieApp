package com.example.movieapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.data.movie.Movie

@Database(entities = [Movie::class], version = 3, exportSchema = false)
abstract class FavouritesDatabase : RoomDatabase() {

    abstract fun favouritesDatabaseDao() : FavouritesDatabaseDao

}