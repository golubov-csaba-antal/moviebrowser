package com.zappyware.moviebrowser.network.tmdb.response

import com.zappyware.moviebrowser.network.tmdb.data.entities.TmdbVideo

data class VideoListResponse(
    val results: List<TmdbVideo>
)
