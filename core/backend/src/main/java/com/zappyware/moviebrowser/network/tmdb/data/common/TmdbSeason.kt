package com.zappyware.moviebrowser.network.tmdb.data.common

import com.google.gson.annotations.SerializedName
import com.zappyware.moviebrowser.data.widget.SeasonWidget
import com.zappyware.moviebrowser.network.tmdb.data.coverUrl
import java.util.Date

data class TmdbSeason(
    @SerializedName("air_date")
    val airDate: Date?,
    @SerializedName("episode_count")
    val episodeCount: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("season_number")
    val seasonNumber: Int,
    @SerializedName("vote_average")
    val voteAverage: Float,
)

fun TmdbSeason.toSeasonWidget(): SeasonWidget =
    SeasonWidget(
        airDate = airDate,
        episodeCount = episodeCount,
        id = id,
        name = name,
        overview = overview,
        posterPath = posterPath?.let { coverUrl(it) },
        seasonNumber = seasonNumber,
        voteAverage = voteAverage,
    )
