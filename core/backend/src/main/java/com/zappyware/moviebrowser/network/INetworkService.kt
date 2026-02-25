package com.zappyware.moviebrowser.network

import com.zappyware.moviebrowser.data.MediaType
import com.zappyware.moviebrowser.data.screen.DetailScreen
import com.zappyware.moviebrowser.data.tray.TrayWidget
import com.zappyware.moviebrowser.data.widget.GenreWidget

interface INetworkService {
    suspend fun getGenres(mediaType: MediaType): List<GenreWidget>
    suspend fun fetchLandingTrays(): List<TrayWidget>
    suspend fun fetchDetailScreen(contentId: Long, mediaType: MediaType): DetailScreen?
}
