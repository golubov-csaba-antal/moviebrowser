package com.zappyware.moviebrowser.data.widget

import com.zappyware.moviebrowser.data.MediaType
import java.util.Date

data class MovieWidget(
    val adult: Boolean,
    val backdropPath: String?,
    override val id: String,
    val title: String,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String,
    val posterUrl: String?,
    val smallPosterUrl: String?,
    val mediaType: MediaType,
    val genre: String,
    val popularity: Float,
    val releaseDate: Date?,
    val firstAirDate: Date?,
    val video: Boolean,
    val voteAverage: Float?,
    val voteCount: Int,
): Widget(id)
