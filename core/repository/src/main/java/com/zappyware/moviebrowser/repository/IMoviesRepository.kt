package com.zappyware.moviebrowser.repository

import com.zappyware.moviebrowser.common.ui.ViewState
import com.zappyware.moviebrowser.data.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface IMoviesRepository {
    val movies: MutableStateFlow<List<Movie>>
    val detailsId: MutableStateFlow<Long>

    suspend fun fetchMovies(): Flow<ViewState<List<Movie>>>
    suspend fun changeFavorite(favorite: Boolean)
}
