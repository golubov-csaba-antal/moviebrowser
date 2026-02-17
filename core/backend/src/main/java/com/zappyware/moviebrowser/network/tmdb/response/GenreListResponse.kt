package com.zappyware.moviebrowser.network.tmdb.response

import com.zappyware.moviebrowser.network.tmdb.data.TmdbGenre

data class GenreListResponse(
    val genres: List<TmdbGenre>
)
