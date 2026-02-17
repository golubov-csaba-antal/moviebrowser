package com.zappyware.moviebrowser.network.tmdb

import com.zappyware.moviebrowser.data.Genre
import com.zappyware.moviebrowser.data.Movie
import com.zappyware.moviebrowser.network.INetworkService
import com.zappyware.moviebrowser.network.tmdb.data.AUTH
import com.zappyware.moviebrowser.network.tmdb.data.BASE_URL
import com.zappyware.moviebrowser.network.tmdb.data.toGenre
import com.zappyware.moviebrowser.network.tmdb.data.toMovie
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class TmdbService @Inject constructor(
    private val retrofit: Retrofit
): INetworkService {

    private val language: String by lazy { Locale.getDefault().let { "${it.language}-${it.displayCountry}" } }

    private val cachedGenres = HashMap<String,List<Genre>>()

    private val tmdbApi: TmdbApi by lazy {
        retrofit.newBuilder()
            .baseUrl(BASE_URL)
            .build()
            .create(TmdbApi::class.java)
    }

    override suspend fun getGenres(mediaType: String): List<Genre> =
        cachedGenres.getOrPut(mediaType) {
            tmdbApi.getGenres(AUTH, mediaType, language).genres.map {
                it.toGenre()
            }
        }

    override suspend fun getTrendingMovies(): List<Movie> =
        tmdbApi.getTrendingMovies(AUTH, language).results.map { tmdbMovie ->
            tmdbMovie.toMovie().also { movie ->
                movie.genres = getGenres(tmdbMovie.mediaType)
                    .filter { tmdbMovie.genreIds.contains(it.id) }
                    .joinToString(", ") { it.title.lowercase() }
            }
        }
}
