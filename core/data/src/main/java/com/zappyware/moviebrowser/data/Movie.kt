package com.zappyware.moviebrowser.data

data class Movie(
    val id: Long,
    val title: String,
    var genres: String,
    val overview: String?,
    val smallCoverUrl: String?,
    val coverUrl: String?,
    val rating: Float,
    var isFavorite: Boolean,
)
