package com.zappyware.moviebrowser.data.widget

import com.zappyware.moviebrowser.data.common.Language

data class ImageWidget(
    val category: String,
    val aspectRatio: Float,
    val width: Int,
    val height: Int,
    val language: Language,
    val imagePath: String,
    val voteAverage: Float,
    val voteCount: Int,
)
