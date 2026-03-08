package com.zappyware.moviebrowser.data.widget

import com.zappyware.moviebrowser.data.common.Gender

data class CreatorWidget(
    val id: String,
    val creditId: String,
    val name: String,
    val originalName: String,
    val gender: Gender,
    val profilePath: String?,
)
