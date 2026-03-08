package com.zappyware.moviebrowser.repository

import com.zappyware.moviebrowser.data.MediaType
import com.zappyware.moviebrowser.data.screen.DetailScreen
import com.zappyware.moviebrowser.data.tray.TrayWidget
import com.zappyware.moviebrowser.database.dao.FavoritesDao
import com.zappyware.moviebrowser.database.entity.toMBFavoriteMovie
import com.zappyware.moviebrowser.network.INetworkService
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val service: INetworkService,
    private val favoritesDao: FavoritesDao,
): IMoviesRepository {

    override suspend fun fetchLandingTrays(): List<TrayWidget> =
        service.fetchLandingTrays()

    override suspend fun changeFavorite(id: String, isFavorite: Boolean) {
        val isMovieFavoriteEntity = id.toMBFavoriteMovie()
        if(isFavorite) {
            favoritesDao.addToFavorites(isMovieFavoriteEntity)
        } else {
            favoritesDao.removeFromFavorites(isMovieFavoriteEntity)
        }
    }

    override suspend fun getIsFavoriteMovieById(id: String): Boolean =
        favoritesDao.isFavorites(id) != 0

    override suspend fun fetchDetailWidget(contentId: String, mediaType: MediaType): DetailScreen? =
        service.fetchDetailScreen(contentId, mediaType)
}
