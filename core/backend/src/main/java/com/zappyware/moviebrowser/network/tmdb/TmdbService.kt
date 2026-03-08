package com.zappyware.moviebrowser.network.tmdb

import com.google.gson.Gson
import com.zappyware.moviebrowser.data.MediaType
import com.zappyware.moviebrowser.data.page.PageWidget
import com.zappyware.moviebrowser.data.tray.HorizontalPagerTrayWidget
import com.zappyware.moviebrowser.data.tray.ShowcaseTrayWidget
import com.zappyware.moviebrowser.data.tray.TrayWidget
import com.zappyware.moviebrowser.data.widget.GenreWidget
import com.zappyware.moviebrowser.data.widget.MovieWidget
import com.zappyware.moviebrowser.network.INetworkService
import com.zappyware.moviebrowser.network.tmdb.data.AUTH
import com.zappyware.moviebrowser.network.tmdb.data.BASE_URL
import com.zappyware.moviebrowser.network.tmdb.data.entities.TmdbMovie
import com.zappyware.moviebrowser.network.tmdb.data.entities.toGenre
import com.zappyware.moviebrowser.network.tmdb.data.entities.toMovie
import com.zappyware.moviebrowser.network.tmdb.data.enums.TmdbInterval
import com.zappyware.moviebrowser.network.tmdb.data.enums.TmdbMediaType
import com.zappyware.moviebrowser.network.tmdb.data.enums.toTmdbMediaType
import com.zappyware.moviebrowser.network.tmdb.data.page.TmdbPage
import com.zappyware.moviebrowser.network.tmdb.response.MovieListResponse
import com.zappyware.moviebrowser.network.tmdb.util.PathFactory
import com.zappyware.moviebrowser.network.tmdb.util.TmdbDateAdapter
import com.zappyware.moviebrowser.network.tmdb.util.TmdbMediaTypeDeserializer
import com.zappyware.moviebrowser.network.tmdb.util.TmdbMediaTypeSerializer
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class TmdbService @Inject constructor(
    okHttpClient: OkHttpClient,
    gson: Gson,
): INetworkService {

    private val language: String by lazy { Locale.getDefault().let { "${it.language}-${it.country}" } }

    private val cachedGenres = mutableMapOf<TmdbMediaType, List<GenreWidget>>()

    private val tmdbApi: TmdbApi by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(gson.toConverterFactory())
            .baseUrl(BASE_URL)
            .addConverterFactory(PathFactory())
            .build()
            .create(TmdbApi::class.java)
    }

    override suspend fun getGenres(mediaType: MediaType): List<GenreWidget> =
        cachedGenres.getOrPut(mediaType.toTmdbMediaType()) {
            tmdbApi.getGenres(AUTH, mediaType.toTmdbMediaType(), language)
                .genres
                .map {
                    it.toGenre()
                }
        }

    private suspend fun fetchWidgetList(mediaType: MediaType, serviceCall: suspend (TmdbMediaType) -> MovieListResponse): List<MovieWidget> {
        val tmdbMovies = serviceCall(mediaType.toTmdbMediaType()).results
        val genres = getGenres(mediaType).associateBy { it.id }

        return tmdbMovies.map { tmdbMovie ->
            tmdbMovie.toMovie(
                mediaType = mediaType,
                genres = tmdbMovie.genreIds.takeIf { !it.isNullOrEmpty() }?.mapNotNull { genres[it]?.title }.orEmpty()
            )
        }
    }

    private suspend fun fetchWidget(mediaType: MediaType, serviceCall: suspend (TmdbMediaType) -> TmdbMovie): MovieWidget {
        val tmdbMovie = serviceCall(mediaType.toTmdbMediaType())
        val genres = getGenres(mediaType).associateBy { it.id }

        return tmdbMovie.toMovie(
            mediaType = mediaType,
            genres = tmdbMovie.genreIds.takeIf { !it.isNullOrEmpty() }?.mapNotNull { genres[it]?.title }.orEmpty()
        )
    }

    private suspend fun fetchPage(mediaType: MediaType, serviceCall: suspend (TmdbMediaType) -> TmdbPage): PageWidget {
        val tmdbPage = serviceCall(mediaType.toTmdbMediaType())
        val genres = getGenres(mediaType).associateBy { it.id }

        return tmdbPage.toPageWidget(
            mediaType = mediaType,
            genres = tmdbPage.getGenres().takeIf { !it.isNullOrEmpty() }?.mapNotNull { genres[it]?.title }.orEmpty()
        )
    }

    override suspend fun fetchLandingTrays(): List<TrayWidget> {
        return listOf(
            HorizontalPagerTrayWidget(
                id = "1",
                title = "Daily trending movies",
                widgets = fetchWidgetList(MediaType.MOVIE) { mediaType ->
                    tmdbApi.getTrending(AUTH, mediaType, TmdbInterval.DAY, language)
                },
            ),
            HorizontalPagerTrayWidget(
                id = "2",
                title = "Daily trending shows",
                widgets = fetchWidgetList(MediaType.SHOW) { mediaType ->
                    tmdbApi.getTrending(AUTH, mediaType, TmdbInterval.DAY, language)
                },
            ),
            ShowcaseTrayWidget(
                id = "3",
                title = "Latest show",
                widget = fetchWidget(MediaType.SHOW) { mediaType ->
                    tmdbApi.getLatest(AUTH, mediaType, language)
                },
            ),
            HorizontalPagerTrayWidget(
                id = "4",
                title = "Weekly trending movies",
                widgets = fetchWidgetList(MediaType.MOVIE) { mediaType ->
                    tmdbApi.getTrending(AUTH, mediaType, TmdbInterval.WEEK, language)
                },
            ),
            HorizontalPagerTrayWidget(
                id = "5",
                title = "Weekly trending shows",
                widgets = fetchWidgetList(MediaType.SHOW) { mediaType ->
                    tmdbApi.getTrending(AUTH, mediaType, TmdbInterval.WEEK, language)
                },
            ),
            ShowcaseTrayWidget(
                id = "6",
                title = "Latest movie",
                widget = fetchWidget(MediaType.MOVIE) { mediaType ->
                    tmdbApi.getLatest(AUTH, mediaType, language)
                },
            ),
        )
    }

    override suspend fun fetchDetailScreen(contentId: String, mediaType: MediaType): PageWidget {
        return fetchPage(mediaType) { mediaType ->
            tmdbApi.getDetails(AUTH, mediaType, contentId, language, listOf("videos", "images"))
        }
    }
}

private fun Gson.toConverterFactory(): Converter.Factory {
    return GsonConverterFactory.create(
        newBuilder()
            .registerTypeAdapter(Date::class.java, TmdbDateAdapter())
            .registerTypeAdapter(TmdbMediaType::class.java, TmdbMediaTypeSerializer())
            .registerTypeAdapter(TmdbMediaType::class.java, TmdbMediaTypeDeserializer())
            .create()
    )
}
