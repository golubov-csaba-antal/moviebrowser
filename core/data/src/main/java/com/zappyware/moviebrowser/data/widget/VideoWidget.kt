package com.zappyware.moviebrowser.data.widget

import com.zappyware.moviebrowser.data.common.Language
import java.util.Date

data class VideoWidget(
    override val id: String,
    val title: String,
    val originalTitle: String,
    val key: String,
    val site: String,
    val size: Int,
    val type: String,
    val official: Boolean,
    val published: Date,
    val language: Language,
): Widget(id)
