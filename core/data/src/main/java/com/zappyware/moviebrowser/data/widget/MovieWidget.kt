package com.zappyware.moviebrowser.data.widget

import com.zappyware.moviebrowser.data.MediaType

data class MovieWidget(
    override val id: String,
    val mediaType: MediaType,
    val title: String,
    val genres: String,
    val overview: String?,
    val smallCoverUrl: String?,
    val coverUrl: String?,
    val rating: Float,
): Widget(id)
