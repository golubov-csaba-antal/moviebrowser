package com.zappyware.moviebrowser.repository

import com.zappyware.moviebrowser.data.MovieWidget
import com.zappyware.moviebrowser.database.dao.FavoritesDao
import com.zappyware.moviebrowser.database.dao.MoviesDao
import com.zappyware.moviebrowser.database.entity.toMBFavoriteMovie
import com.zappyware.moviebrowser.database.entity.toMBMovie
import com.zappyware.moviebrowser.database.entity.toMovie
import com.zappyware.moviebrowser.network.INetworkService
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val service: INetworkService,
    private val moviesDao: MoviesDao,
    private val favoritesDao: FavoritesDao,
): IMoviesRepository {

    override suspend fun fetchMovies(): List<MovieWidget> {
        val movies = service.getTrendingMovies()
        movies.run {
            moviesDao.clearMovies()
            moviesDao.saveMovies(map { it.toMBMovie() })
        }//moviesDao.getMovies().map { it.toMovie() }
        movies.forEach {
            it.isFavorite = favoritesDao.isFavorites(it.id) != 0
        }
        return movies
    }

    override suspend fun changeFavorite(id: Long, isFavorite: Boolean) {
        val isMovieFavoriteEntity = id.toMBFavoriteMovie()
        if(isFavorite) {
            favoritesDao.addToFavorites(isMovieFavoriteEntity)
        } else {
            favoritesDao.removeFromFavorites(isMovieFavoriteEntity)
        }
    }

    override suspend fun getMovieById(id: Long): MovieWidget? =
        moviesDao.getMovieByContentId(id)?.toMovie()

    override suspend fun getIsFavoriteMovieById(id: Long): Boolean =
        favoritesDao.isFavorites(id) != 0
}
