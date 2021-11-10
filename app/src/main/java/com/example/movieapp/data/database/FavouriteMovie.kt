package com.example.movieapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movies_table")
data class FavouriteMovie(
    @PrimaryKey
    val id: Long,

    @ColumnInfo(name = "movie_title")
    val title: String,

    @ColumnInfo(name = "movie_overview")
    val overview: String,

    @ColumnInfo(name = "movie_backdrop_path")
    val backdrop_path: String,

    @ColumnInfo(name = "movie_poster_path")
    val poster_path: String,

    @ColumnInfo(name = "movie_vote_average")
    val vote_average: String,

    @ColumnInfo(name = "movie_release_date")
    val release_date: String,
)


