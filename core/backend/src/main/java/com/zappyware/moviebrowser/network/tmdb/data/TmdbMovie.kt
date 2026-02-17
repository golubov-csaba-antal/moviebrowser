package com.zappyware.moviebrowser.network.tmdb.data

import com.google.gson.annotations.SerializedName
import com.zappyware.moviebrowser.data.Movie
import java.util.Date

data class TmdbMovie(

    @SerializedName("adult")
    val adult: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("id")
    val id: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("media_type")
    val mediaType: String,

    @SerializedName("genre_ids")
    val genreIds: LongArray,

    @SerializedName("popularity")
    val popularity: Float,

    @SerializedName("release_date")
    val releaseDate: Date,

    @SerializedName("video")
    val video: Boolean,

    @SerializedName("vote_average")
    val voteAverage: Float,

    @SerializedName("vote_count")
    val voteCount: Int
)

fun TmdbMovie.toMovie(): Movie = Movie(
    id = id,
    title = title,
    genres = "",
    overview = overview,
    smallCoverUrl = smallCoverUrl(posterPath),
    coverUrl = coverUrl(posterPath),
    rating = voteAverage,
    isFavorite = false,
)
