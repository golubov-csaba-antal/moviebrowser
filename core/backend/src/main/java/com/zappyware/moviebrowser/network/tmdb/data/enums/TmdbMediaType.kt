package com.zappyware.moviebrowser.network.tmdb.data.enums

import com.zappyware.moviebrowser.data.MediaType

enum class TmdbMediaType(val mediaType: String) {
    MOVIE("movie"),
    SHOW("tv"),
}

fun TmdbMediaType.toMediaType(): MediaType =
    when(this) {
        TmdbMediaType.MOVIE -> MediaType.MOVIE
        TmdbMediaType.SHOW -> MediaType.SHOW
    }

fun MediaType.toTmdbMediaType(): TmdbMediaType =
    when(this) {
        MediaType.MOVIE -> TmdbMediaType.MOVIE
        MediaType.SHOW -> TmdbMediaType.SHOW
    }
