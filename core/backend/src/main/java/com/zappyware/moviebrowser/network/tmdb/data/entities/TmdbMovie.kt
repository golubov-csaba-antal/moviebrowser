package com.zappyware.moviebrowser.network.tmdb.data.entities

import com.google.gson.annotations.SerializedName
import com.zappyware.moviebrowser.data.MediaType
import com.zappyware.moviebrowser.data.widget.MovieWidget
import com.zappyware.moviebrowser.network.tmdb.data.coverUrl
import com.zappyware.moviebrowser.network.tmdb.data.enums.TmdbMediaType
import com.zappyware.moviebrowser.network.tmdb.data.enums.toMediaType
import com.zappyware.moviebrowser.network.tmdb.data.smallCoverUrl
import java.util.Date

data class TmdbMovie(

    @SerializedName("adult")
    val adult: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("id")
    val id: String,

    @SerializedName("title", ["name"])
    val title: String,

    @SerializedName("original_language")
    val originalLanguage: String?,

    @SerializedName("original_title")
    val originalTitle: String?,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("media_type")
    val mediaType: TmdbMediaType?,

    @SerializedName("genre_ids")
    val genreIds: List<String>?,

    @SerializedName("popularity")
    val popularity: Float,

    @SerializedName("release_date")
    val releaseDate: Date?,

    @SerializedName("first_air_date")
    val firstAirDate: Date?,

    @SerializedName("video")
    val video: Boolean,

    @SerializedName("vote_average")
    val voteAverage: Float,

    @SerializedName("vote_count")
    val voteCount: Int,

)

fun TmdbMovie.toMovie(mediaType: MediaType, genres: List<String>): MovieWidget = MovieWidget(
    adult = adult,
    backdropPath = backdropPath,
    id = id,
    title = title,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    posterUrl = posterPath?.let { coverUrl(it) }.orEmpty(),
    smallPosterUrl = posterPath?.let { smallCoverUrl(it) }.orEmpty(),
    mediaType = mediaType,
    genre = genres.joinToString(", ") { it.lowercase() },
    popularity = popularity,
    releaseDate = releaseDate,
    firstAirDate = firstAirDate,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
)

fun TmdbMovie.toMovie(genres: List<String>): MovieWidget = MovieWidget(
    adult = adult,
    backdropPath = backdropPath,
    id = id,
    title = title,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    posterUrl = posterPath?.let { coverUrl(it) }.orEmpty(),
    smallPosterUrl = posterPath?.let { smallCoverUrl(it) }.orEmpty(),
    mediaType = mediaType?.toMediaType() ?: MediaType.MOVIE,
    genre = genres.joinToString(", ") { it.lowercase() },
    popularity = popularity,
    releaseDate = releaseDate,
    firstAirDate = firstAirDate,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
)
