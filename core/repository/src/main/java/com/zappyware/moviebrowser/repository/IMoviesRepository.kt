package com.zappyware.moviebrowser.repository

import com.zappyware.moviebrowser.data.MediaType
import com.zappyware.moviebrowser.data.page.PageWidget
import com.zappyware.moviebrowser.data.tray.TrayWidget

interface IMoviesRepository {
    suspend fun fetchLandingTrays(): List<TrayWidget>
    suspend fun changeFavorite(id: String, isFavorite: Boolean)
    suspend fun getIsFavoriteMovieById(id: String): Boolean
    suspend fun fetchDetailWidget(contentId: String, mediaType: MediaType): PageWidget?
}
