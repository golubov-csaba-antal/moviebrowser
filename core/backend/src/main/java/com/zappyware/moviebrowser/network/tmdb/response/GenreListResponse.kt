package com.zappyware.moviebrowser.network.tmdb.response

import com.zappyware.moviebrowser.network.tmdb.data.entities.TmdbGenre

data class GenreListResponse(
    val genres: List<TmdbGenre>
)
