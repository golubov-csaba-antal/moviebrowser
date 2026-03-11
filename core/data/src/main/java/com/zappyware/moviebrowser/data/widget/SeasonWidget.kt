package com.zappyware.moviebrowser.data.widget

import java.util.Date

data class SeasonWidget(
    val airDate: Date?,
    val episodeCount: Int,
    override val id: String,
    val name: String,
    val overview: String,
    val posterPath: String?,
    val smallPosterPath: String?,
    val seasonNumber: Int,
    val voteAverage: Float?,
): Widget(id)
