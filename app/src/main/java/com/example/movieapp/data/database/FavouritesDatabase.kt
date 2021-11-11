package com.example.movieapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.data.movie.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class FavouritesDatabase : RoomDatabase() {

    abstract val favouritesDatabaseDao : FavouritesDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: FavouritesDatabase? = null

        fun getInstance(context: Context) : FavouritesDatabase {
            synchronized(this){
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            FavouritesDatabase::class.java,
                            "favourite_movies_database"
                            )
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}