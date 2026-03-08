package com.zappyware.moviebrowser.network.tmdb.response

import com.zappyware.moviebrowser.network.tmdb.data.entities.TmdbImage

data class ImageListResponse(
    val results: List<TmdbImage>
)
