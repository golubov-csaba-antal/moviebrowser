package com.zappyware.moviebrowser.network

import com.zappyware.moviebrowser.data.Genre
import com.zappyware.moviebrowser.data.Movie

interface INetworkService {
    suspend fun getGenres(mediaType: String): List<Genre>
    suspend fun getTrendingMovies(): List<Movie>
}
