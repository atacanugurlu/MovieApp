package com.example.movieapp.data.movie

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
data class Movie(
    @PrimaryKey
    @SerializedName("id") val id: Long,

    @ColumnInfo(name = "movie_title")
    @SerializedName("title") val title: String?,

    @ColumnInfo(name = "movie_overview")
    @SerializedName("overview") val overview: String?,

    @ColumnInfo(name = "movie_backdrop_path")
    @SerializedName("backdrop_path") val backdropPath: String?,

    @ColumnInfo(name = "movie_poster_path")
    @SerializedName("poster_path") val posterPath: String?,

    @ColumnInfo(name = "movie_vote_average")
    @SerializedName("vote_average") val rating: Float,

    @ColumnInfo(name = "movie_release_date")
    @SerializedName("release_date") val releaseDate: String?,

    @ColumnInfo(name = "movie_is_favourite")
    var isFavourite: Boolean = false

) : Parcelable {
    constructor(source: Parcel) : this(
        source.readLong(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readFloat(),
        source.readString()
    )
    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(title)
        writeString(overview)
        writeString(backdropPath)
        writeString(posterPath)
        writeFloat(rating)
        writeString(releaseDate)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie = Movie(source)
            override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
        }
    }
}
