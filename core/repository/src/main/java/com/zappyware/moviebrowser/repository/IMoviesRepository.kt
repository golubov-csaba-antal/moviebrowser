package com.zappyware.moviebrowser.repository

import com.zappyware.moviebrowser.data.MovieWidget

interface IMoviesRepository {
    suspend fun fetchMovies(): List<MovieWidget>
    suspend fun changeFavorite(id: Long, isFavorite: Boolean)
    suspend fun getMovieById(id: Long): MovieWidget?
    suspend fun getIsFavoriteMovieById(id: Long): Boolean
}
