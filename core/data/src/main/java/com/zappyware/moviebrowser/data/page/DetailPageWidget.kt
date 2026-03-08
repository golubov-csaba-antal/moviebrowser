package com.zappyware.moviebrowser.data.page

import com.zappyware.moviebrowser.data.common.ContentType
import com.zappyware.moviebrowser.data.common.Language
import com.zappyware.moviebrowser.data.widget.CreatorWidget
import com.zappyware.moviebrowser.data.widget.ImageWidget
import com.zappyware.moviebrowser.data.widget.MovieWidget
import com.zappyware.moviebrowser.data.widget.SeasonWidget
import com.zappyware.moviebrowser.data.widget.VideoWidget
import java.util.Date

data class DetailPageWidget(
    val adult: Boolean,
    val backdropPath: String?,
    val created: List<CreatorWidget>,
    val episodeRunTime: List<Int>,
    val firstAirDate: Date?,
    val genreIds: List<String>?,
    val homepage: String,
    override val id: String,
    val isInProduction: Boolean,
    val languages: List<String>,
    val lastAirDate: Date?,
    val lastEpisodeToAir: MovieWidget?,
    val title: String,
    val nextEpisodeToAir: MovieWidget?,
    val networks: List<String>,
    val episodesCount: Int,
    val seasonsCount: Int,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String?,
    val productionCompanies: List<String>,
    val productionCountries: List<String>,
    val seasons: List<SeasonWidget>,
    val dubs: List<Language>,
    val status: String,
    val tagline: String,
    val type: ContentType,
    val voteAverage: Float,
    val voteCount: Int,
    val isVideo: Boolean,
    val videos: List<VideoWidget>,
    val images: List<ImageWidget>,
): PageWidget(id)
