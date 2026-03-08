package com.zappyware.moviebrowser.data.widget

import com.zappyware.moviebrowser.data.MediaType
import com.zappyware.moviebrowser.data.common.Gender

data class PeopleWidget(
    val isAdult: Boolean?,
    override val id: String,
    val creditId: String?,
    val name: String,
    val originalName: String,
    val mediaType: MediaType,
    val popularity: Float,
    val gender: Gender,
    val department: String?,
    val profilePath: String?,
    val relatedContents: List<MovieWidget>?,
): Widget(id)
