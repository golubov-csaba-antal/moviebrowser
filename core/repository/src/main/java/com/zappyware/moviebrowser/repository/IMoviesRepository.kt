package com.zappyware.moviebrowser.repository

import com.zappyware.moviebrowser.common.ui.ViewState
import com.zappyware.moviebrowser.data.MovieWidget
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {
    suspend fun fetchMovies(): Flow<ViewState<List<MovieWidget>>>
    suspend fun changeFavorite(id: Long, isFavorite: Boolean)
    suspend fun getMovieById(id: Long): MovieWidget?
    suspend fun getIsFavoriteMovieById(id: Long): Boolean
}
