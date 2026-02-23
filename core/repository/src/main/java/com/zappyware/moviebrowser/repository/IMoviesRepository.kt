package com.zappyware.moviebrowser.repository

import com.zappyware.moviebrowser.data.MovieWidget
import com.zappyware.moviebrowser.data.HorizontalPagerTrayWidget

interface IMoviesRepository {
    suspend fun fetchTrendingMoviesTray(): HorizontalPagerTrayWidget
    suspend fun changeFavorite(id: Long, isFavorite: Boolean)
    suspend fun getMovieById(id: Long): MovieWidget?
    suspend fun getIsFavoriteMovieById(id: Long): Boolean
}
