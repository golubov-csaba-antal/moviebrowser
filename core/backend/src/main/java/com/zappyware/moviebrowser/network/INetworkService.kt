package com.zappyware.moviebrowser.network

import com.zappyware.moviebrowser.data.GenreWidget
import com.zappyware.moviebrowser.data.MovieWidget

interface INetworkService {
    suspend fun getGenres(mediaType: String): List<GenreWidget>
    suspend fun getTrendingMovies(): List<MovieWidget>
}
