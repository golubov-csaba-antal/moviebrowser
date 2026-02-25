package com.zappyware.moviebrowser.repository

import com.zappyware.moviebrowser.data.MediaType
import com.zappyware.moviebrowser.data.screen.DetailScreen
import com.zappyware.moviebrowser.data.tray.TrayWidget

interface IMoviesRepository {
    suspend fun fetchLandingTrays(): List<TrayWidget>
    suspend fun changeFavorite(id: Long, isFavorite: Boolean)
    suspend fun getIsFavoriteMovieById(id: Long): Boolean
    suspend fun fetchDetailWidget(contentId: Long, mediaType: MediaType): DetailScreen?
}
