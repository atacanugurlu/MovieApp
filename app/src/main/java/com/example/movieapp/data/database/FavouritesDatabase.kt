package com.example.movieapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.data.movie.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class FavouritesDatabase : RoomDatabase() {

    abstract fun favouritesDatabaseDao() : FavouritesDatabaseDao

/*
    //gidecek, daggerla
    companion object{
        @Volatile
        private var INSTANCE: FavouritesDatabase? = null


        // bunun yerine, singleton yapmak için dagger kullan
        // dagger in multithread
        // Provide
        fun getInstance(context: Context) : FavouritesDatabase {
            synchronized(this){
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(   //buradan sonra dagger
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

 */

}