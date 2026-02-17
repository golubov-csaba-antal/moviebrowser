package com.zappyware.moviebrowser.network.tmdb.response

import com.zappyware.moviebrowser.network.tmdb.data.TmdbMovie

data class MovieListResponse(
    val results: List<TmdbMovie>
)
