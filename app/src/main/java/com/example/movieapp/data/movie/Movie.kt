package com.example.movieapp.data.movie

import com.google.gson.annotations.SerializedName
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movies_table")
data class Movie(
    @PrimaryKey
    @SerializedName("id") val id: Long,

    @ColumnInfo(name = "movie_title")
    @SerializedName("title") val title: String,

    @ColumnInfo(name = "movie_overview")
    @SerializedName("overview") val overview: String,

    @ColumnInfo(name = "movie_backdrop_path")
    @SerializedName("backdrop_path") val backdropPath: String,

    @ColumnInfo(name = "movie_poster_path")
    @SerializedName("poster_path") val posterPath: String,

    @ColumnInfo(name = "movie_vote_average")
    @SerializedName("vote_average") val rating: Float,

    @ColumnInfo(name = "movie_release_date")
    @SerializedName("release_date") val releaseDate: String,

    @ColumnInfo(name = "movie_is_favourite")
    var isFavourite: Boolean = false
)

data class GetMoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<Movie>,
    @SerializedName("total_pages") val pages: Int
)