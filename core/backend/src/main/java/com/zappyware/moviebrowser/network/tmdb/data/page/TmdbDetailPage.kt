package com.zappyware.moviebrowser.network.tmdb.data.page

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.zappyware.moviebrowser.data.MediaType
import com.zappyware.moviebrowser.data.common.findContentType
import com.zappyware.moviebrowser.data.page.DetailPageWidget
import com.zappyware.moviebrowser.data.page.PageWidget
import com.zappyware.moviebrowser.network.tmdb.adapter.TmdbImagesAdapter
import com.zappyware.moviebrowser.network.tmdb.adapter.TmdbVideosAdapter
import com.zappyware.moviebrowser.network.tmdb.data.common.TmdbCompany
import com.zappyware.moviebrowser.network.tmdb.data.common.TmdbCountry
import com.zappyware.moviebrowser.network.tmdb.data.common.TmdbLanguage
import com.zappyware.moviebrowser.network.tmdb.data.common.TmdbSeason
import com.zappyware.moviebrowser.network.tmdb.data.common.toLanguage
import com.zappyware.moviebrowser.network.tmdb.data.common.toSeasonWidget
import com.zappyware.moviebrowser.network.tmdb.data.coverUrl
import com.zappyware.moviebrowser.network.tmdb.data.entities.TmdbGenre
import com.zappyware.moviebrowser.network.tmdb.data.entities.TmdbPeople
import com.zappyware.moviebrowser.network.tmdb.data.entities.TmdbImage
import com.zappyware.moviebrowser.network.tmdb.data.entities.TmdbMovie
import com.zappyware.moviebrowser.network.tmdb.data.entities.TmdbVideo
import com.zappyware.moviebrowser.network.tmdb.data.entities.toPeopleWidget
import com.zappyware.moviebrowser.network.tmdb.data.entities.toImageWidget
import com.zappyware.moviebrowser.network.tmdb.data.entities.toMovie
import com.zappyware.moviebrowser.network.tmdb.data.entities.toVideoWidget
import java.util.Date

data class TmdbDetailPage(

    @SerializedName("adult")
    val adult: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("created_by")
    val created: List<TmdbPeople>?,

    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>?,

    @SerializedName("first_air_date")
    val firstAirDate: Date?,

    @SerializedName("genres")
    val genres: List<TmdbGenre>?,

    @SerializedName("homepage")
    val homepage: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("in_production")
    val isInProduction: Boolean,

    @SerializedName("languages")
    val languages: List<String>?,

    @SerializedName("last_air_date")
    val lastAirDate: Date?,

    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: TmdbMovie?,

    @SerializedName("title", ["name"])
    val title: String,

    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: TmdbMovie?,

    @SerializedName("networks")
    val networks: List<TmdbCompany>?,

    @SerializedName("number_of_episodes")
    val episodesCount: Int,

    @SerializedName("number_of_seasons")
    val seasonsCount: Int,

    @SerializedName("origin_country")
    val originCountry: List<String>?,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_title", alternate = ["original_name"])
    val originalTitle: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("popularity")
    val popularity: Float,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("production_companies")
    val productionCompanies: List<TmdbCompany>?,

    @SerializedName("production_countries")
    val productionCountries: List<TmdbCountry>?,

    @SerializedName("seasons")
    val seasons: List<TmdbSeason>?,

    @SerializedName("spoken_languages")
    val dubs: List<TmdbLanguage>?,

    @SerializedName("status")
    val status: String,

    @SerializedName("tagline")
    val tagline: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("vote_average")
    val voteAverage: Float,

    @SerializedName("vote_count")
    val voteCount: Int,

    @SerializedName("video")
    val isVideo: Boolean,

    @SerializedName("videos")
    @JsonAdapter(TmdbVideosAdapter::class)
    val videos: List<TmdbVideo>?,

    @SerializedName("images")
    @JsonAdapter(TmdbImagesAdapter::class)
    val images: List<TmdbImage>?,
): TmdbPage() {

    override fun toPageWidget(
        mediaType: MediaType,
    ): PageWidget =
        DetailPageWidget(
            adult = adult,
            backdropPath = backdropPath?.let { coverUrl(it) },
            created = created?.map { it.toPeopleWidget() } ?: emptyList(),
            episodeRunTime = episodeRunTime ?: emptyList(),
            firstAirDate = firstAirDate,
            genres = genres?.map { it.name }?.joinToString(", ") { it.lowercase() }.orEmpty(),
            homepage = homepage,
            id = id,
            isInProduction = isInProduction,
            languages = languages ?: emptyList(),
            lastAirDate = lastAirDate,
            lastEpisodeToAir = lastEpisodeToAir?.toMovie(mediaType, genres?.map { it.name }.orEmpty()),
            title = title,
            nextEpisodeToAir = nextEpisodeToAir?.toMovie(mediaType, genres?.map { it.name }.orEmpty()),
            networks = emptyList(),
            episodesCount = episodesCount,
            seasonsCount = seasonsCount,
            originCountry = originCountry ?: emptyList(),
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath?.let { coverUrl(it) },
            productionCompanies = emptyList(),
            productionCountries = emptyList(),
            seasons = seasons?.map { it.toSeasonWidget() } ?: emptyList(),
            dubs = dubs?.map { it.toLanguage() } ?: emptyList(),
            status = status,
            tagline = tagline,
            type = findContentType(type),
            voteAverage = voteAverage,
            voteCount = voteCount,
            isVideo = isVideo,
            videos = videos?.map { it.toVideoWidget() } ?: emptyList(),
            images = images?.filter { !it.imagePath.isNullOrEmpty() }?.map { it.toImageWidget() } ?: emptyList()
        )
}
