package com.zappyware.moviebrowser.network.tmdb.data.entities

import com.google.gson.annotations.SerializedName
import com.zappyware.moviebrowser.data.MediaType
import com.zappyware.moviebrowser.data.widget.MovieWidget
import com.zappyware.moviebrowser.network.tmdb.data.coverUrl
import com.zappyware.moviebrowser.network.tmdb.data.smallCoverUrl

data class TmdbMovie(

    @SerializedName("adult")
    val adult: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("title", ["name"])
    val title: String,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("genre_ids")
    val genreIds: List<String>?,

    @SerializedName("popularity")
    val popularity: Float,

    @SerializedName("video")
    val video: Boolean,

    @SerializedName("vote_average")
    val voteAverage: Float,

    @SerializedName("vote_count")
    val voteCount: Int,

)

fun TmdbMovie.toMovie(mediaType: MediaType, genres: List<String>): MovieWidget = MovieWidget(
    id = id,
    mediaType = mediaType,
    title = title,
    genres = genres.joinToString(", ") { it.lowercase() },
    overview = overview,
    smallCoverUrl = posterPath?.let { smallCoverUrl(it) }.orEmpty(),
    coverUrl = posterPath?.let { coverUrl(it) }.orEmpty(),
    rating = voteAverage,
)
