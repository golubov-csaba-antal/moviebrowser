package com.zappyware.moviebrowser.network.tmdb

import com.zappyware.moviebrowser.data.GenreWidget
import com.zappyware.moviebrowser.data.HorizontalPagerTrayWidget
import com.zappyware.moviebrowser.network.INetworkService
import com.zappyware.moviebrowser.network.tmdb.data.AUTH
import com.zappyware.moviebrowser.network.tmdb.data.BASE_URL
import com.zappyware.moviebrowser.network.tmdb.data.toGenre
import com.zappyware.moviebrowser.network.tmdb.data.toMovie
import retrofit2.Retrofit
import java.util.Locale
import javax.inject.Inject

class TmdbService @Inject constructor(
    private val retrofit: Retrofit
): INetworkService {

    private val language: String by lazy { Locale.getDefault().let { "${it.language}-${it.country}" } }

    private val cachedGenres = mutableMapOf<String, List<GenreWidget>>()

    private val tmdbApi: TmdbApi by lazy {
        retrofit.newBuilder()
            .baseUrl(BASE_URL)
            .build()
            .create(TmdbApi::class.java)
    }

    override suspend fun getGenres(mediaType: String): List<GenreWidget> =
        cachedGenres.getOrPut(mediaType) {
            tmdbApi.getGenres(AUTH, mediaType, language)
                .genres
                .map {
                    it.toGenre()
                }
        }

    override suspend fun getTrendingMoviesTray(): HorizontalPagerTrayWidget {
        val tmdbMovies = tmdbApi.getTrendingMovies(AUTH, language).results
        val genres = getGenres("movie").associateBy { it.id }

        val movies = tmdbMovies.map { tmdbMovie ->
            tmdbMovie.toMovie(
                genres = tmdbMovie.genreIds.mapNotNull { genres[it]?.title }
            )
        }

        return HorizontalPagerTrayWidget(
            title = "Trending movies",
            widgets = movies
        )
    }
}
